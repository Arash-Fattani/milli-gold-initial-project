package gold.milli.initialproject.entity;

import gold.milli.initialproject.entity.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@ToString
public class CreateUserRequestDto {
    @NotBlank(message = "Username cannot be blank")
    @Size(max = 50, message = "Username must be less than 50 characters")
    private String username;
    @Size(min = 8, max = 20, message = "password must be between 6 and 20 characters")
    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Password must contain at least one letter and one number")
    private String password;
    @NotBlank(message = "email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;
    @NotEmpty(message = "You must have a role")
    private List<Role> roles;
}
