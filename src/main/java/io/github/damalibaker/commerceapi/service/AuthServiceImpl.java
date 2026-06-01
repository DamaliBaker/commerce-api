package io.github.damalibaker.commerceapi.service;

import io.github.damalibaker.commerceapi.dto.request.LoginRequest;
import io.github.damalibaker.commerceapi.dto.response.AuthResponse;
import io.github.damalibaker.commerceapi.entity.UserEntity;
import io.github.damalibaker.commerceapi.exception.InvalidCredentialsException;
import io.github.damalibaker.commerceapi.repository.UserRepository;
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
