package com.example.healthcheck.user.responsedto;

import com.example.healthcheck.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class UserGetResponseDTO {

    @Schema(description="유저 id", example = "1", required = true)
    private UUID id;

    @Schema(description="유저 이름", example = "Kang", required = true)
    private String name;

    @Schema(description="유저 암호", example = "1234", required = true)
    private String password;

    public UserGetResponseDTO(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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


}
