package org.example.service.impl;

import org.example.constant.UserConstant;
import org.example.exception.UserAlreadyExistException;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceRules {

    private final UserRepository userRepository;

    public UserServiceRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(final String id) throws UserNotFoundException {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(UserConstant.USER_NOT_FOUND));
    }

    public User findByUsername(final String username) throws UserNotFoundException {
        return userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserConstant.USER_NOT_FOUND));
    }

    public void checkIfUserExists(final String username) throws UserAlreadyExistException{
        final Optional<User> user = userRepository.getUserByUsername(username);

        if(user.isPresent()) {
            throw new UserAlreadyExistException(UserConstant.USER_ALREADY_EXIST);
        }
    }


}
