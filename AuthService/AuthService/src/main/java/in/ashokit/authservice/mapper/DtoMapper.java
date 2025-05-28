package in.ashokit.authservice.mapper;

import in.ashokit.authservice.entity.User;
import in.ashokit.authservice.dto.UserDto;
import in.ashokit.authservice.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private static final ModelMapper mapper = new ModelMapper();
    private DtoMapper() {
        throw new UnsupportedOperationException("Utility class");
    }
    public static User userdtoToUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public static UserResponseDto userConvertToDTOResponse(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

}
