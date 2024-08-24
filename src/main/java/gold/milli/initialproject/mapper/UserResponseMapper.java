package gold.milli.initialproject.mapper;

import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponseDto responseFromUser(User request);
}
