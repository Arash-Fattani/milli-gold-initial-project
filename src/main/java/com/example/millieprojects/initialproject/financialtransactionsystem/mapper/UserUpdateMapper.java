package com.example.millieprojects.initialproject.financialtransactionsystem.mapper;

import com.example.millieprojects.initialproject.financialtransactionsystem.entity.User;
import com.example.millieprojects.initialproject.financialtransactionsystem.entity.UserDTOCreate;
import com.example.millieprojects.initialproject.financialtransactionsystem.entity.UserDTOUpdate;
import com.example.millieprojects.initialproject.financialtransactionsystem.entity.enums.Role;
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
