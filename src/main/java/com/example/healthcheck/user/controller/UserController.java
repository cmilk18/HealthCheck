package com.example.healthcheck.user.controller;

import com.example.healthcheck.user.requestdto.UserCreateRequestDTO;
import com.example.healthcheck.user.requestdto.UserGetRequestDTO;
import com.example.healthcheck.user.requestdto.UserLoginRequestDTO;
import com.example.healthcheck.user.requestdto.UserPutRequestDTO;
import com.example.healthcheck.user.responsedto.UserGetResponseDTO;
import com.example.healthcheck.user.service.UserService;

import com.example.healthcheck.util.http.HttpResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//http://localhost:8080/swagger-ui/index.html#/UserController/loginUsers
//http://localhost:8080/actuator/health
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Tag(name = "UserController", description = "유저를 생성하는 컨트롤러입니다.")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary="유저 생성", description="유저정보를 받아 유저를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "fail", description = "실패했습니다.")})
    @PostMapping("/user")
    public HttpResponse createUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO){
        return userService.createUser(userCreateRequestDTO);
    }

    @Operation(summary="유저 하나 가져오기", description="특정 유저의 정보를 받아옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "fail", description = "실패했습니다.")})
    @GetMapping("/user/{userId}")
    public UserGetResponseDTO getUser(@PathVariable UUID userId){
        return userService.getUser(userId);
    }

    @Operation(summary="유저 전체 가져오기", description="전체 유저의 정보를 받아옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "fail", description = "실패했습니다.")})
    @GetMapping("/users")
    public List<UserGetResponseDTO> getUsers(){
        return userService.getUsers();
    }

    @Operation(summary="유저 정보 수정하기", description="한명의 유저정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "fail", description = "실패했습니다.")})
    @PatchMapping("/user")
    public HttpResponse putUser(@RequestBody UserPutRequestDTO userPutRequestDTO){
        return userService.putUser(userPutRequestDTO);
    }

    @Operation(summary="유저 정보 삭제하기", description="유저를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "fail", description = "실패했습니다.")})
    @DeleteMapping("/user/{userId}")
    public HttpResponse deleteUser(@PathVariable UUID userId){
        return userService.deleteUser(userId);
    }

    @Operation(summary="유저 전체 삭제", description="전체 유저의 정보를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공했습니다."),
            @ApiResponse(responseCode = "fail", description = "실패했습니다.")})
    @DeleteMapping("/users")
    public HttpResponse deleteUsers(){
        return userService.deleteUsers();
    }

    @Operation(summary="유저 로그인", description="로그인합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인했습니다."),
            @ApiResponse(responseCode = "fail", description = "실패했습니다.")})
    @PostMapping("/user/login")
    public String loginUsers(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        return userService.loginUsers(userLoginRequestDTO);
    }



    @Operation(summary="이메일 검증 1단계", description="이메일로 검증 코드를 보냅니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용 가능 합니다."),
            @ApiResponse(responseCode = "500", description = "이미 사용중 입니다.")})
    @PostMapping("/user/verify")
    public String verifyEmail(@RequestBody UserCreateRequestDTO userCreateRequestDTO){
        return userService.verifyEmail(userCreateRequestDTO);
    }
}
