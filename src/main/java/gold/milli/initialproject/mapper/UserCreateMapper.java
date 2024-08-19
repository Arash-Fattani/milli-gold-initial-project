package gold.milli.initialproject.mapper;

import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.UserDTOCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCreateMapper {
        @Mapping(source = "username", target = "username")
        @Mapping(source = "password", target = "password")
        @Mapping(source = "email", target = "email")
        @Mapping(source = "roles", target = "roles")
        User UserCreateFromRequest (UserDTOCreate request);
        @Mapping(source = "username", target = "username")
        @Mapping(source = "password", target = "password")
        @Mapping(source = "email", target = "email")
        @Mapping(source = "roles", target = "roles")
        UserDTOCreate UserDTOCreateFromUser(User bean);

}
