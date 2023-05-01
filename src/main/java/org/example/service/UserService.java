package org.example.service;

import org.example.dto.UserDto;
import org.example.request.UserPostRequest;
import org.example.request.UserPutRequest;
import org.example.response.DataResponse;
import org.example.response.Response;

import java.util.List;

public interface UserService {
    DataResponse<UserDto> getById(String id);
    DataResponse<List<UserDto>> getAll();
    DataResponse<String> create(UserPostRequest request);
    DataResponse<String> update(String id, UserPutRequest request);
    Response delete(String id);
}
