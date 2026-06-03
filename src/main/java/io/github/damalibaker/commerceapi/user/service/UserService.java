package io.github.damalibaker.commerceapi.user.service;

import io.github.damalibaker.commerceapi.user.dto.RegisterRequest;
import io.github.damalibaker.commerceapi.user.entity.UserEntity;

public interface UserService {
    UserEntity register(RegisterRequest request);
    UserEntity getCurrentUser();
}
