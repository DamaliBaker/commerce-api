package io.github.damalibaker.commerceapi.user.mapper;

import io.github.damalibaker.commerceapi.user.dto.UserResponse;
import io.github.damalibaker.commerceapi.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
