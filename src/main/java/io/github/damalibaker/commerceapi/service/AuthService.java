package io.github.damalibaker.commerceapi.service;

import io.github.damalibaker.commerceapi.dto.request.LoginRequest;
import io.github.damalibaker.commerceapi.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
}
