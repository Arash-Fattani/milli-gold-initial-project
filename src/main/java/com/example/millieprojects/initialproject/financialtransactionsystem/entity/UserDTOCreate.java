package com.example.millieprojects.initialproject.financialtransactionsystem.entity;

import com.example.millieprojects.initialproject.financialtransactionsystem.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class UserDTOCreate {

    private String username;

    private String password;

    private String email;

    private List<Role> roles;
}
