package gold.milli.initialproject.mapper;

import gold.milli.initialproject.entity.UserDTOCreate;
import gold.milli.initialproject.entity.UserDTOUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserUpdateMapper {
    @Mapping(target = "password", constant = "password")
    @Mapping(target = "roles",  expression = "java(java.util.Collections.emptyList())")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    UserDTOCreate UserUpdateFromRequest (UserDTOUpdate request);
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    UserDTOUpdate UserDTOUpdateFromUser(UserDTOCreate bean);
}
