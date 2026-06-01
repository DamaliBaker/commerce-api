package io.github.damalibaker.commerceapi.service;

import io.github.damalibaker.commerceapi.dto.request.RegisterRequest;
import io.github.damalibaker.commerceapi.entity.UserEntity;

public interface UserService {
    UserEntity register(RegisterRequest request);
}
