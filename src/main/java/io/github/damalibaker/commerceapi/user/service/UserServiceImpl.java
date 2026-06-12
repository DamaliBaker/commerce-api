package io.github.damalibaker.commerceapi.user.service;

import io.github.damalibaker.commerceapi.user.dto.RegisterRequest;
import io.github.damalibaker.commerceapi.user.entity.UserEntity;
import io.github.damalibaker.commerceapi.user.enums.Role;
import io.github.damalibaker.commerceapi.exception.user.EmailAlreadyExistsException;
import io.github.damalibaker.commerceapi.exception.auth.InvalidCredentialsException;
import io.github.damalibaker.commerceapi.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException(request.getEmail());
        }


        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_CUSTOMER)
                .build();

        return userRepository.save(user);
    }

    @Override
    public UserEntity getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new InvalidCredentialsException();
        }

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);
    }
}
