package org.example.util;

import org.example.constant.UserConstant;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

public final class UserUtil {
    private UserUtil() {
        throw new AssertionError();
    }

    public static User findById(UserRepository userRepository, String id) {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(UserConstant.USER_NOT_FOUND));
    }
}
