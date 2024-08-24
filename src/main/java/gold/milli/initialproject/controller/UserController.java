package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.CreateUserRequestDto;
import gold.milli.initialproject.entity.UpdateUserRequestDto;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.UserDto;
import gold.milli.initialproject.mapper.UserServiceMapper;
import gold.milli.initialproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController {
    private final UserService userService;
    private final UserServiceMapper userServiceMapper;

    @PostMapping("/user")
    @Operation(
            summary = "create a user",
            description = "Save a newly registered user"
    )
    public UserDto createUser(@RequestBody @Valid CreateUserRequestDto userRequest) {
        User user = userServiceMapper.createUserRequestMapper.userFromCreateRequest(userRequest);
        return userServiceMapper.userResponseMapper.responseFromUser(userService.createUser(user));
    }

    @GetMapping("/users")
    @Operation(
            summary = "fetch all users",
            description = "fetch all of the registered users"
    )
    public List<UserDto> fetchAllUsers() {
        return userService.fetchAllUsers().stream()
                .map(userServiceMapper.userResponseMapper::responseFromUser).collect(Collectors.toList());
    }

    @PutMapping("/users/{userId}")
    @Operation(
            summary = "update a user",
            description = "update user's username or email"
    )
    public UserDto updateUser(@RequestBody @Valid UpdateUserRequestDto updateUserRequestDto, @PathVariable Integer userId) {
        try {
            User user = userServiceMapper.updateUserRequestMapper.userFromUpdateRequest(updateUserRequestDto);
            System.out.println(user);
            return userServiceMapper.userResponseMapper.responseFromUser(userService.updateUser(user, userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/users/{userId}")
    @Operation(
            summary = "delete a user",
            description = "delete a user by their unique id"
    )
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}
