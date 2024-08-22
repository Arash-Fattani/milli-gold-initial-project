package gold.milli.initialproject.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class UpdateUserRequestDto {
    private String username;
    private String email;

}
