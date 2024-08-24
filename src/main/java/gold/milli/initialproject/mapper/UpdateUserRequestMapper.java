package gold.milli.initialproject.mapper;

import gold.milli.initialproject.entity.UpdateUserRequestDto;
import gold.milli.initialproject.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateUserRequestMapper {

    User userFromUpdateRequest(UpdateUserRequestDto request);
}
