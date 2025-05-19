package in.ashokit.AuthService.ServiceImplementation;

import in.ashokit.AuthService.Entity.User;
import in.ashokit.AuthService.ExceptionHandler.AuthServiceException;
import in.ashokit.AuthService.JwtTokenGenerate.JwtTokenUtils;
import in.ashokit.AuthService.Repository.UserRepository;
import in.ashokit.AuthService.Service.UserService;
import in.ashokit.AuthService.Utils.EmailUtils;
import in.ashokit.AuthService.dto.UserDto;
import in.ashokit.AuthService.dto.UserResponseDto;
import in.ashokit.AuthService.mapper.DtoMapper;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {


    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private DtoMapper mapper;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private JwtTokenUtils jwtToken;

    @Override
    public UserResponseDto saveUser(UserDto userDto) throws MessagingException {
        try {


            Optional<User> existingUser = userRepo.findByEmail(userDto.getEmail());
            if (existingUser.isPresent()) {
                throw new AuthServiceException("Email already registered", "409");
            }

            userDto.setPassword(pwdEncoder.encode(userDto.getPassword()));

            User user = userRepo.save(mapper.userdtoToUser(userDto));

            String otp = emailUtils.generateVerificationCode();
            emailUtils.sendVerificationEmail(user.getEmail(), user.getName(), otp);
            return mapper.userConvertToDTOResponse(user);
        } catch (MessagingException e) {
            throw new AuthServiceException("Email Sending failed", "500");

        } catch (Exception e) {
            throw new AuthServiceException("Registration Failed", "500");
        }

    }

    @Override
    public String loginUser(UserDto userDto) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());


            Authentication authenticate = authManager.authenticate(token);
            if (authenticate.isAuthenticated()) {
                return jwtToken.generateToken(userDto.getEmail());
            } else {
                throw new RuntimeException("Authentication Failed");
            }
        } catch (BadCredentialsException e) {
            throw new AuthServiceException("Invalid Credential", "401");
        }
    }

    @Override
    public List<UserResponseDto> getAllUser() {

        return userRepo.findAll().stream().map(DtoMapper::userConvertToDTOResponse).toList();


    }

    @Override
    public UserResponseDto updateUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(userDto.getEmail());
            user.setContact(user.getContact());
            user.setDob(user.getDob());
            user.setName(user.getName());
            user.setPassword(pwdEncoder.encode(user.getPassword()));
            user.setRoles(user.getRoles());

            return mapper.userConvertToDTOResponse(userRepo.save(user));
        } else {
            throw new AuthServiceException("user not found", "404");
        }

    }

    @Override
    public void deleteUser(Integer id) {

        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            userRepo.deleteById(id);
        } else {
            throw new AuthServiceException("user not found on this id", "404");

        }

    }
}
