package gold.milli.initialproject.entity;

import lombok.*;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateUserRequestDto {
    private String username;
    private String email;

}
