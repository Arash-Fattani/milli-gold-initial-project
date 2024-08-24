package gold.milli.initialproject.entity;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
public class UpdateUserRequestDto {
    private String username;
    private String email;

}
