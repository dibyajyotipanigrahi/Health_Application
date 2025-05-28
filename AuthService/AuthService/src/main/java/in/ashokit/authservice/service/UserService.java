package in.ashokit.authservice.service;

import in.ashokit.authservice.dto.UserDto;
import in.ashokit.authservice.dto.UserResponseDto;


import java.util.List;

public interface UserService {
    public UserResponseDto saveUser(UserDto userDto) ;

    public String loginUser(UserDto userDto);

    public UserResponseDto getById(Integer id);
    public List<UserResponseDto> getAllUser();

    public UserResponseDto updateUser(Integer id, UserDto userDto);

    public void deleteUser(Integer id);
}
