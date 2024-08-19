package gold.milli.initialproject.entity;

import gold.milli.initialproject.entity.enums.Role;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class UserDTOCreate {

    private String username;

    private String password;

    private String email;

    private List<Role> roles;
}
