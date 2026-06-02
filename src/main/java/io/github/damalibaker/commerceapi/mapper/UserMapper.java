package io.github.damalibaker.commerceapi.mapper;

import io.github.damalibaker.commerceapi.dto.response.UserResponse;
import io.github.damalibaker.commerceapi.entity.UserEntity;
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
