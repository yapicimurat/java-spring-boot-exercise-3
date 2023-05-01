package org.example.service;

import org.apache.logging.log4j.util.Strings;
import org.example.constant.RoleConstant;
import org.example.constant.UserConstant;
import org.example.dto.UserDto;
import org.example.exception.InvalidRoleException;
import org.example.exception.RoleNotFoundException;
import org.example.exception.UserAlreadyExistException;
import org.example.exception.UserNotFoundException;
import org.example.model.Role;
import org.example.model.RoleType;
import org.example.model.User;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.example.request.UserPostRequest;
import org.example.request.UserPutRequest;
import org.example.response.DataResponse;
import org.example.response.Response;
import org.example.response.SuccessDataResponse;
import org.example.util.EntityUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public DataResponse<UserDto> getById(String id) {
        final User user = findById(id);
        final UserDto userDto = modelMapper.map(user, UserDto.class);

        return new SuccessDataResponse<>(userDto, UserConstant.FETCH_SUCCESSFULLY);
    }

    @Override
    public DataResponse<List<UserDto>> getAll() {
        final List<User> userList = userRepository.findAll();
        final List<UserDto> userDtoList = EntityUtil.listToListDtoConverter(modelMapper, userList, UserDto.class);

        return new SuccessDataResponse<>(userDtoList, UserConstant.FETCH_SUCCESSFULLY);
    }

    @Override
    public DataResponse<String> create(UserPostRequest request) {
        checkIfUserExists(request.getUsername());
        checkIfValidRole(request.getRoleName());

        final Role role = findRoleByRoleType(RoleType.valueOf(request.getRoleName()));
        final User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );

        user.getRoles().add(role);

        final User persistedUser = userRepository.save(user);

        return new SuccessDataResponse<>(persistedUser.getId().toString(), UserConstant.CREATE_SUCCESSFULLY);
    }

    @Override
    public DataResponse<String> update(String id, UserPutRequest request) {
        final User user = findById(id);

        if(!Objects.isNull(request.getPassword()) && Strings.isNotEmpty(request.getPassword())) {
            user.setPassword(request.getPassword());
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        userRepository.save(user);

        return new SuccessDataResponse<>(user.getId().toString(), UserConstant.UPDATE_SUCCESSFULLY);
    }

    @Override
    public Response delete(final String id) {
        //TODO: Complete here...
        return null;
    }

    private User findById(final String id) throws UserNotFoundException {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(UserConstant.USER_NOT_FOUND));
    }

    private Optional<User> findByUsername(final String username) {
        return userRepository.getUserByUsername(username);
    }

    private void checkIfUserExists(final String username) throws UserAlreadyExistException {
        if(findByUsername(username).isPresent()) {
            throw new UserAlreadyExistException(UserConstant.USER_ALREADY_EXIST);
        }
    }

    private void checkIfValidRole(String roleName) {
        for(RoleType roleType : RoleType.values()) {
            if(roleType.getRoleName().equals(roleName)) {
                return;
            }
        }

        throw new InvalidRoleException(RoleConstant.INVALID_ROLE_ERROR);
    }

    private Role findRoleByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType)
                .orElseThrow(() -> new RoleNotFoundException(RoleConstant.NOT_FOUND));
    }
}
