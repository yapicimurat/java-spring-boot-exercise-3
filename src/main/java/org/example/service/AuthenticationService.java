package org.example.service;

import org.example.dto.AuthenticationDto;
import org.example.request.AuthenticationRequest;
import org.example.response.DataResponse;

public interface AuthenticationService {
    DataResponse<AuthenticationDto> authenticate(AuthenticationRequest authenticationRequest);
}
