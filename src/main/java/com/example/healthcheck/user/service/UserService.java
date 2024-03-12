package com.example.healthcheck.user.service;

import com.example.healthcheck.user.requestdto.UserCreateRequestDTO;
import com.example.healthcheck.user.requestdto.UserCreateRequestUpdateDTO;
import com.example.healthcheck.user.requestdto.UserLoginRequestDTO;
import com.example.healthcheck.user.requestdto.UserPutRequestDTO;
import com.example.healthcheck.user.responsedto.UserGetResponseDTO;
import com.example.healthcheck.util.http.HttpResponse;
import jakarta.mail.MessagingException;


import java.util.List;
import java.util.UUID;

public interface UserService {
    HttpResponse createUser(UserCreateRequestDTO userCreateRequestDTO);

    UserGetResponseDTO getUser(UUID userId);

    List<UserGetResponseDTO> getUsers();

    HttpResponse putUser(UserPutRequestDTO userPutRequestDTO);

    HttpResponse deleteUser(UUID userId);

    HttpResponse deleteUsers();

    String loginUsers(UserLoginRequestDTO userLoginRequestDTO);

    String verifyEmail(UserCreateRequestDTO userCreateRequestDTO) throws MessagingException;

    String verifyEmailCode(UserCreateRequestUpdateDTO userCreateRequestUpdateDTO);
}
