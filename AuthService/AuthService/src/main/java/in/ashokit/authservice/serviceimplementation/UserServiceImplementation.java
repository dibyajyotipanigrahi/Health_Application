package in.ashokit.authservice.serviceimplementation;

import in.ashokit.authservice.dto.EmailRequest;
import in.ashokit.authservice.dto.UserDto;
import in.ashokit.authservice.dto.UserResponseDto;
import in.ashokit.authservice.entity.Roles;
import in.ashokit.authservice.entity.User;
import in.ashokit.authservice.exceptionhandler.AuthServiceException;
import in.ashokit.authservice.feignclient.EmailClient;
import in.ashokit.authservice.jwttokengenerate.JwtTokenUtils;
import in.ashokit.authservice.mapper.DtoMapper;
import in.ashokit.authservice.repository.RoleRepository;
import in.ashokit.authservice.repository.UserRepository;
import in.ashokit.authservice.service.UserService;
import in.ashokit.authservice.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private JwtTokenUtils jwtToken;

    @Override
    public UserResponseDto saveUser(UserDto userDto) {
        try {
            Optional<User> existingUser = userRepo.findByEmail(userDto.getEmail());
            if (existingUser.isPresent()) {
                throw new AuthServiceException("Email already registered", "409");
            }

            userDto.setPassword(pwdEncoder.encode(userDto.getPassword()));

            User user = DtoMapper.userdtoToUser(userDto);

            List<Roles> roleEntities = userDto.getRoles().stream()
                    .map(roleName -> roleRepo.findByroleName(roleName)
                            .orElseGet(() -> {
                                Roles newRole = new Roles();
                                newRole.setName(roleName);
                                return roleRepo.save(newRole);
                            }))
                    .toList();

            user.setRoles(roleEntities);

            user = userRepo.save(user);

            String otp = emailUtils.generateVerificationCode();
            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setTo(user.getEmail());
            emailRequest.setSubject("Registered Successfully");
            emailRequest.setBody("Hi " + user.getName() +otp+ ", welcome to HealthCare App!");
            emailClient.sendEmail(emailRequest);

            return DtoMapper.userConvertToDTOResponse(user);

        } catch (Exception e) {
            throw new AuthServiceException("Registration Failed: " + e.getMessage(), "500");
        }
    }

    @Override
    public String loginUser(UserDto userDto) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

            Authentication authenticate = authManager.authenticate(token);
            if (authenticate.isAuthenticated()) {
                return jwtToken.generateToken(userDto.getEmail(), userDto.getRoles());
            } else {
                throw new AuthServiceException("Authentication Failed", "500");
            }
        } catch (Exception e) {
            throw new AuthServiceException("Invalid Credential", "401");
        }
    }



    @Override
    public UserResponseDto getById(Integer id) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            return DtoMapper.userConvertToDTOResponse(byId.get());
        } else {
            throw new AuthServiceException("Data not found", "500");
        }
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepo.findAll()
                .stream()
                .map(DtoMapper::userConvertToDTOResponse)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(userDto.getEmail());
            user.setContact(userDto.getContact());
            user.setDob(userDto.getDob());
            user.setName(userDto.getName());
            user.setPassword(pwdEncoder.encode(userDto.getPassword()));

            List<Roles> roleEntities = userDto.getRoles().stream()
                    .map(roleName -> roleRepo.findByroleName(roleName)
                            .orElseGet(() -> {
                                Roles newRole = new Roles();
                                newRole.setName(roleName);
                                return roleRepo.save(newRole);
                            }))
                    .toList();

            user.setRoles(roleEntities);

            return DtoMapper.userConvertToDTOResponse(userRepo.save(user));
        } else {
            throw new AuthServiceException("User not found", "404");
        }
    }

    @Override
    public void deleteUser(Integer id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            userRepo.deleteById(id);
        } else {
            throw new AuthServiceException("User not found on this ID", "404");
        }
    }
}
