package com.example.millieprojects.initialproject.financialtransactionsystem.entity;

import lombok.*;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@Getter
@Setter
public class UserDTOUpdate {
    private String username;
    private String email;

}
