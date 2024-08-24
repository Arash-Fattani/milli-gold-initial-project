package gold.milli.initialproject.mapper;

import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.CreateUserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CreateUserRequestMapper {
        User userFromCreateRequest(CreateUserRequestDto request);
}
