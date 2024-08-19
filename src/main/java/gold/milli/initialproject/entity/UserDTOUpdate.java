package gold.milli.initialproject.entity;

import lombok.*;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class UserDTOUpdate {
    private String username;
    private String email;

}
