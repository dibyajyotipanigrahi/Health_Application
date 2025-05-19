package in.ashokit.AuthService.mapper;

import in.ashokit.AuthService.Entity.User;
import in.ashokit.AuthService.dto.UserDto;
import in.ashokit.AuthService.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static User userdtoToUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public static UserResponseDto userConvertToDTOResponse(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

}
