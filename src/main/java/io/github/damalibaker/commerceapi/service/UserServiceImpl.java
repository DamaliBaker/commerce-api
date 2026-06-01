package io.github.damalibaker.commerceapi.service;

import io.github.damalibaker.commerceapi.dto.request.RegisterRequest;
import io.github.damalibaker.commerceapi.entity.UserEntity;
import io.github.damalibaker.commerceapi.entity.enums.Role;
import io.github.damalibaker.commerceapi.exception.EmailAlreadyExistsEception;
import io.github.damalibaker.commerceapi.repository.UserRepository;
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


        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        return userRepository.save(user);
    }
}
