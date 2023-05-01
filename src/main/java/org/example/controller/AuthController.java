package org.example.controller;

import org.example.dto.AuthenticationDto;
import org.example.request.AuthenticationRequest;
import org.example.request.UserPostRequest;
import org.example.response.DataResponse;
import org.example.service.AuthenticationService;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AuthController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<DataResponse<String>> createUser(@Valid @RequestBody UserPostRequest request) {
        final DataResponse<String> response = userService.create(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<DataResponse<AuthenticationDto>> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        final DataResponse<AuthenticationDto> response = authenticationService.authenticate(request);

        return ResponseEntity.ok(response);
    }
}
