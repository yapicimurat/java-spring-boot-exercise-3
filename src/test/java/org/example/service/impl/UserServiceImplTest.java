package org.example.service.impl;

import org.example.dto.UserDto;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserServiceRules userServiceRules;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeAll
    public void setup(){
        userRepository = Mockito.mock(UserRepository.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        userServiceRules = new UserServiceRules(userRepository);
        modelMapper = Mockito.mock(ModelMapper.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

        userService = new UserServiceImpl(
                userRepository,
                roleRepository,
                modelMapper,
                passwordEncoder,
                userServiceRules);
    }

    @Test
    void whenGetByIdCalledWithExistingUserId_ShouldReturn_UserDTO() {
        String userId = UUID.randomUUID().toString();

        User expectedUser = new User();
        expectedUser.setFirstName("Murat");
        expectedUser.setLastName("YAPICI");
        expectedUser.setUsername("yapicimurat");
        expectedUser.setPassword(passwordEncoder.encode("123"));

        Optional<User> optionalUser = Optional.of(expectedUser);

        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setFirstName(expectedUser.getFirstName());
        expectedUserDto.setLastName(expectedUser.getLastName());
        expectedUserDto.setUsername(expectedUser.getUsername());

        Mockito.when(userRepository.findById(UUID.fromString(userId)))
                        .thenReturn(optionalUser);
        Mockito.when(modelMapper.map(expectedUser, UserDto.class))
                .thenReturn(expectedUserDto);

        UserDto result = userService.getById(userId).data;

        Mockito.verify(userRepository).findById(UUID.fromString(userId));
        Mockito.verify(modelMapper).map(expectedUser, UserDto.class);

        Assertions.assertEquals(result, expectedUserDto);

    }

    @Test
    void whenGetByIdCalledWithNonExistentUserId_ShouldThrow_UserNotFoundException() {
        UUID nonExistentUserId = UUID.randomUUID();

        Mockito.when(userRepository.findById(nonExistentUserId))
                .thenThrow(UserNotFoundException.class);
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getById(nonExistentUserId.toString()));
    }

}