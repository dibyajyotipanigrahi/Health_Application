package in.ashokit.AuthService.Service;

import in.ashokit.AuthService.dto.UserDto;
import in.ashokit.AuthService.dto.UserResponseDto;
import jakarta.mail.MessagingException;

import java.util.List;

public interface UserService {
    public UserResponseDto saveUser(UserDto userDto) throws MessagingException;

    public String loginUser(UserDto userDto);

    public List<UserResponseDto> getAllUser();

    public UserResponseDto updateUser(Integer id, UserDto UserDto);

    public void deleteUser(Integer id);
}
