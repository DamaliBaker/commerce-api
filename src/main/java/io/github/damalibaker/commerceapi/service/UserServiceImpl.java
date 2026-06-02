package io.github.damalibaker.commerceapi.service;

import io.github.damalibaker.commerceapi.dto.request.RegisterRequest;
import io.github.damalibaker.commerceapi.entity.UserEntity;
import io.github.damalibaker.commerceapi.entity.enums.Role;
import io.github.damalibaker.commerceapi.exception.EmailAlreadyExistsEception;
import io.github.damalibaker.commerceapi.exception.InvalidCredentialsException;
import io.github.damalibaker.commerceapi.repository.UserRepository;
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
            throw new EmailAlreadyExistsEception(request.getEmail());
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
