package gold.milli.initialproject.mapper;

import gold.milli.initialproject.entity.CreateUserRequestDto;
import gold.milli.initialproject.entity.UpdateUserRequestDto;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User request);
    User FromUpdateToUser(UpdateUserRequestDto request);
    User fromCreateToUser(CreateUserRequestDto request);

}
