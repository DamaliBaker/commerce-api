package io.github.damalibaker.commerceapi.user.controller;

import io.github.damalibaker.commerceapi.user.mapper.UserMapper;
import io.github.damalibaker.commerceapi.user.dto.UserResponse;
import io.github.damalibaker.commerceapi.user.service.UserService;
import io.github.damalibaker.commerceapi.user.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid RegisterRequest request) {
        return userMapper.toUserResponse(userService.register(request));
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser() {
        return userMapper.toUserResponse(userService.getCurrentUser());
    }
}
