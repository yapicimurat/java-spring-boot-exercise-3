package org.example.service.impl;

import org.example.constant.UserConstant;
import org.example.dto.AuthenticationDto;
import org.example.model.User;
import org.example.request.AuthenticationRequest;
import org.example.response.DataResponse;
import org.example.response.SuccessDataResponse;
import org.example.service.AuthenticationService;
import org.example.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserServiceRules userServiceRules;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserServiceRules userServiceRules) {
        this.authenticationManager = authenticationManager;
        this.userServiceRules = userServiceRules;
    }

    @Override
    public DataResponse<AuthenticationDto> authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword())
        );

        final User user = userServiceRules.findByUsername(authenticationRequest.getUsername());
        final String accessToken = JwtUtil.generateJWT(user.getUsername());
        final AuthenticationDto authenticationDto = new AuthenticationDto(accessToken);

        return new SuccessDataResponse<>(authenticationDto, UserConstant.AUTHENTICATE_SUCCESSFULLY);
    }
}
