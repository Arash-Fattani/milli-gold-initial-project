package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.CreateUserRequestDto;
import gold.milli.initialproject.entity.UpdateUserRequestDto;
import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.UserDto;
import gold.milli.initialproject.mapper.UserMapper;
import gold.milli.initialproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/user")
    @Operation(
            summary = "create a user",
            description = "Save a newly registered user"
    )
    public UserDto createUser(@RequestBody @Valid CreateUserRequestDto userRequest) {
        return userMapper.toUserDto(userService.createUser(userRequest));
    }

    @GetMapping("/users")
    @Operation(
            summary = "fetch all users",
            description = "fetch all of the registered users"
    )
    public Page<UserDto> fetchAllUsers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        Page<User> userPage = userService.fetchAllUsers(page, size, sortBy, direction);
        List<UserDto> userDtoList = userPage.getContent().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, userPage.getPageable(), userPage.getTotalElements());
    }

    @PutMapping("/users/{userId}")
    @Operation(
            summary = "update a user",
            description = "update user's username or email"
    )
    public UserDto updateUser(@RequestBody @Valid UpdateUserRequestDto updateUserRequestDto, @PathVariable Long userId) throws Exception {
        return userMapper.toUserDto(userService.updateUser(updateUserRequestDto, userId));
    }

    @DeleteMapping("/users/{userId}")
    @Operation(
            summary = "delete a user",
            description = "delete a user by their unique id"
    )
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
