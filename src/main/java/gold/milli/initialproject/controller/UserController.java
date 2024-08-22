package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.CreateUserRequestDto;
import gold.milli.initialproject.entity.UpdateUserRequestDto;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.UserResponseDto;
import gold.milli.initialproject.mapper.UserServiceMapper;
import gold.milli.initialproject.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final UserServiceMapper userServiceMapper;

    @PostMapping("/users/create")
    @Operation(
            summary = "create a user",
            description = "Save a newly registered user"
    )
    public UserResponseDto createUser(@RequestBody CreateUserRequestDto userRequest) {

        User user = userServiceMapper.createUserRequestMapper.userFromCreateRequest(userRequest);
        return userServiceMapper.userResponseMapper.responseFromUser(userServiceImpl.createUser(user));
    }

    @GetMapping("/users")
    @Operation(
            summary = "fetch all users",
            description = "fetch all of the registered users"
    )
    public List<UserResponseDto> fetchAllUsers() {
        return userServiceImpl.fetchAllUsers().stream()
                .map(userServiceMapper.userResponseMapper::responseFromUser).collect(Collectors.toList());
    }

    @PutMapping("/users/{userId}/update")
    @Operation(
            summary = "update a user",
            description = "update user's username or email"
    )
    public UserResponseDto updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto, @PathVariable Integer userId) {
        try {
            User user = userServiceMapper.updateUserRequestMapper.userFromUpdateRequest(updateUserRequestDto);
            return userServiceMapper.userResponseMapper.responseFromUser(userServiceImpl.updateUser(user, userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/users/{userId}/delete")
    @Operation(
            summary = "delete a user",
            description = "delete a user by their unique id"
    )
    public void deleteUser(@PathVariable int userId) {
        userServiceImpl.deleteUser(userId);
    }
}
