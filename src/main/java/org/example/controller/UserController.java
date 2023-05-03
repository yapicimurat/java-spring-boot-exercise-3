package org.example.controller;

import org.example.constant.UserConstant;
import org.example.dto.UserDto;
import org.example.request.UserPostRequest;
import org.example.request.UserPutRequest;
import org.example.response.DataResponse;
import org.example.response.Response;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UserConstant.CONTROLLER_PREFIX)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<UserDto>>> getAll() {
        final DataResponse<List<UserDto>> userDtoList = userService.getAll();

        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<UserDto>> getById(@PathVariable("id") String id) {
        final DataResponse<UserDto> userDto = userService.getById(id);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<DataResponse<String>> create(@Valid @RequestBody UserPostRequest request) {
        final DataResponse<String> userId = userService.create(request);

        return ResponseEntity.ok(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<String>> update(@PathVariable("id") String id, @Valid @RequestBody UserPutRequest request) {
        final DataResponse<String> userId = userService.update(id, request);

        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable("id") String id) {
        final Response response = userService.delete(id);

        return ResponseEntity.ok(response);
    }
}
