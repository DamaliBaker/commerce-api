package io.github.damalibaker.commerceapi.auth.service;

import io.github.damalibaker.commerceapi.auth.dto.AuthResponse;
import io.github.damalibaker.commerceapi.auth.dto.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
}
