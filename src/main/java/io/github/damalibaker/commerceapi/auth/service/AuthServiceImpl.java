package io.github.damalibaker.commerceapi.auth.service;

import io.github.damalibaker.commerceapi.auth.dto.AuthResponse;
import io.github.damalibaker.commerceapi.auth.dto.LoginRequest;
import io.github.damalibaker.commerceapi.user.entity.UserEntity;
import io.github.damalibaker.commerceapi.exception.auth.InvalidCredentialsException;
import io.github.damalibaker.commerceapi.user.repository.UserRepository;
import io.github.damalibaker.commerceapi.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                            .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }


        return new AuthResponse(jwtService.generateToken(user.getEmail()));
    }
}
