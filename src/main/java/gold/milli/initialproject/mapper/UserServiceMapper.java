package gold.milli.initialproject.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserServiceMapper {
    public final CreateUserRequestMapper createUserRequestMapper;
    public final UpdateUserRequestMapper updateUserRequestMapper;
    public final UserResponseMapper userResponseMapper;

}
