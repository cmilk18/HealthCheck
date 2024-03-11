package com.example.healthcheck.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserCreateRequestDTO {

    @Schema(description="유저 이름", example = "Kang", required = true)
    private String name;


    @Schema(description="유저 암호", example = "1234", required = true)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserCreateRequestDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
