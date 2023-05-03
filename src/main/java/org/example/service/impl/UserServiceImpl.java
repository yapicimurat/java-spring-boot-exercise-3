package org.example.service.impl;

import org.apache.logging.log4j.util.Strings;
import org.example.constant.RoleConstant;
import org.example.constant.UserConstant;
import org.example.dto.UserDto;
import org.example.exception.InvalidRoleException;
import org.example.exception.RoleNotFoundException;
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
import org.example.response.SuccessResponse;
import org.example.service.UserService;
import org.example.util.EntityUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserServiceRules userServiceRules;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder,
            UserServiceRules userServiceRules
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userServiceRules = userServiceRules;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public DataResponse<UserDto> getById(String id) {
        final User user = userServiceRules.findById(id);
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
        userServiceRules.checkIfUserExists(request.getUsername());
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
        final User user = userServiceRules.findById(id);

        if(!Objects.isNull(request.getPassword()) && Strings.isNotEmpty(request.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepository.save(user);

        return new SuccessDataResponse<>(user.getId().toString(), UserConstant.UPDATE_SUCCESSFULLY);
    }

    @Override
    public Response delete(final String id) {
        final User user = userServiceRules.findById(id);

        userRepository.delete(user);

        return new SuccessResponse(UserConstant.DELETE_SUCCESSFULLY);
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
