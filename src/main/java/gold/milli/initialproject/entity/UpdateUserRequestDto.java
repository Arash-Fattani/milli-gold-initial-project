package gold.milli.initialproject.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
public class UpdateUserRequestDto {
    @Size(max = 50, message = "Username must be less than 50 characters")
    private String username;
    @Email(message = "Email must be valid")
    private String email;

}
