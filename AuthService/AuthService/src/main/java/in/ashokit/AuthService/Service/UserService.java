package in.ashokit.AuthService.Service;

import in.ashokit.AuthService.dto.UserDto;
import in.ashokit.AuthService.dto.UserResponseDto;


import java.util.List;

public interface UserService {
    public UserResponseDto saveUser(UserDto userDto) ;

    public String loginUser(UserDto userDto);

    public UserResponseDto getById(Integer id);
    public List<UserResponseDto> getAllUser();

    public UserResponseDto updateUser(Integer id, UserDto UserDto);

    public void deleteUser(Integer id);
}
