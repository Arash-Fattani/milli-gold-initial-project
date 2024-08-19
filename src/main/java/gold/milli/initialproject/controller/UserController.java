package gold.milli.initialproject.controller;

import gold.milli.initialproject.entity.User;
import gold.milli.initialproject.entity.UserDTOCreate;
import gold.milli.initialproject.entity.UserDTOUpdate;
import gold.milli.initialproject.mapper.UserCreateMapper;
import gold.milli.initialproject.mapper.UserUpdateMapper;
import gold.milli.initialproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController {
    private UserService userService;
    private UserCreateMapper userMapper;
    private UserUpdateMapper userUpdateMapper;

    @Autowired
    public UserController(UserService userService,
                          UserCreateMapper mapper,
                          UserUpdateMapper userUpdateMapper) {
        this.userMapper = mapper;
        this.userService = userService;
        this.userUpdateMapper = userUpdateMapper;
    }

    @PostMapping("/users/create")
    @Operation(
            summary = "create a user",
            description = "Save a newly registered user"
    )
    public UserDTOCreate saveUser(@RequestBody UserDTOCreate userRequest) {

        User user = this.userMapper.UserCreateFromRequest(userRequest);
        return this.userMapper.UserDTOCreateFromUser(userService.saveUser(user));
    }

    @GetMapping("/users")
    @Operation(
            summary = "fetch all users",
            description = "fetch all of the registered users"
    )
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/users/{id}/profile")
    @Operation(
            summary = "update a user",
            description = "update user's username or email"
    )
    public UserDTOUpdate updateUser(@RequestBody UserDTOUpdate userDTOUpdate, @PathVariable Integer id) {
        try {
            User user = this.userMapper.UserCreateFromRequest(this.userUpdateMapper.UserUpdateFromRequest(userDTOUpdate));
            return this.userUpdateMapper.UserDTOUpdateFromUser(
                    this.userMapper.UserDTOCreateFromUser(userService.updateUser(user, id)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/users/{id}/delete")
    @Operation(
            summary = "delete a user",
            description = "delete a user by their unique id"
    )
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
