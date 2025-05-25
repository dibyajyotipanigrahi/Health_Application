package in.ashokit.AuthService.ServiceImplementation;


import in.ashokit.AuthService.Entity.Roles;
import in.ashokit.AuthService.Entity.User;
import in.ashokit.AuthService.ExceptionHandler.AuthServiceException;
import in.ashokit.AuthService.FeignClient.EmailClient;
import in.ashokit.AuthService.JwtTokenGenerate.JwtTokenUtils;
import in.ashokit.AuthService.Repository.RoleRepository;
import in.ashokit.AuthService.Repository.UserRepository;
import in.ashokit.AuthService.Service.UserService;
import in.ashokit.AuthService.Utils.EmailUtils;
import in.ashokit.AuthService.dto.EmailRequest;
import in.ashokit.AuthService.dto.UserDto;
import in.ashokit.AuthService.dto.UserResponseDto;
import in.ashokit.AuthService.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private DtoMapper mapper;

    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private EmailClient emailClient;

    @Autowired
    private JwtTokenUtils jwtToken;

    @Override
    public UserResponseDto saveUser(UserDto userDto) {
        try {
            // 1. Check if user already exists
            Optional<User> existingUser = userRepo.findByEmail(userDto.getEmail());
            if (existingUser.isPresent()) {
                throw new AuthServiceException("Email already registered", "409");
            }

            // 2. Encrypt password
            userDto.setPassword(pwdEncoder.encode(userDto.getPassword()));

            // 3. Convert DTO to User entity
            User user = mapper.userdtoToUser(userDto);

            // 4. Convert role names to Role entities
            List<Roles> roleEntities = userDto.getRoles().stream().map(roleName -> {
                return roleRepo.findByroleName(roleName)
                        .orElseGet(() -> {
                            Roles newRole = new Roles();
                            newRole.setName(roleName);
                            return roleRepo.save(newRole);
                        });
            }).collect(Collectors.toList());

            user.setRoles(roleEntities);

            // 5. Save user
            user = userRepo.save(user);

            // 6. Send email
            String otp = emailUtils.generateVerificationCode(); // optional
            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setTo(user.getEmail());
            emailRequest.setSubject("Registered Successfully");
            emailRequest.setBody("Hi " + user.getName() + ", welcome to HealthCare App!");
            emailClient.sendEmail(emailRequest);

            // 7. Return response DTO
            return mapper.userConvertToDTOResponse(user);

        } catch (Exception e) {
            throw new AuthServiceException("Registration Failed: " + e.getMessage(), "500");
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
    public UserResponseDto getById(Integer id) {
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            return mapper.userConvertToDTOResponse(user);

        } else {
            throw new AuthServiceException("Data not found", "500");
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
