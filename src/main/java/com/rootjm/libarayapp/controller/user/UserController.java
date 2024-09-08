package com.rootjm.libarayapp.controller.user;

import com.rootjm.libarayapp.controller.user.request.UserUpdateRequest;
import com.rootjm.libarayapp.dto.user.request.UserCreateRequest;
import com.rootjm.libarayapp.dto.user.response.UserResponse;
import com.rootjm.libarayapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userService;

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request)
    {
        userService.saveUser( request );
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {

        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {

        userService.updateUser(request);
    }

    @GetMapping("/user/error-test")
    public void errorTest() {
        throw new IllegalArgumentException();
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {

        userService.deleteUser(name);
    }
}
