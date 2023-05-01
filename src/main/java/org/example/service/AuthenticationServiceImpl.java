package org.example.service;

import org.example.constant.UserConstant;
import org.example.dto.AuthenticationDto;
import org.example.exception.UserAlreadyExistException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.request.AuthenticationRequest;
import org.example.response.DataResponse;
import org.example.response.SuccessDataResponse;
import org.example.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public DataResponse<AuthenticationDto> authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword())
        );

        //user can get directly because getUserByUsername will not return not found.
        final User user = userRepository.getUserByUsername(authenticationRequest.getUsername()).get();
        final String accessToken = JwtUtil.generateJWT(user.getUsername());
        final AuthenticationDto authenticationDto = new AuthenticationDto(accessToken);

        return new SuccessDataResponse<>(authenticationDto, UserConstant.AUTHENTICATE_SUCCESSFULLY);
    }
}
