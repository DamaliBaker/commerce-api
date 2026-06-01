package io.github.damalibaker.commerceapi.controller;

import io.github.damalibaker.commerceapi.dto.request.RegisterRequest;
import io.github.damalibaker.commerceapi.dto.response.UserResponse;
import io.github.damalibaker.commerceapi.mapper.UserMapper;
import io.github.damalibaker.commerceapi.service.UserService;
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
}
