package com.example.healthcheck.user.responsedto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserLoginResponseDTO {
    @Schema(description="유저 id", example = "1", required = true)
    private UUID id;

    @Schema(description="유저 이름", example = "Kang", required = true)
    private String name;

    @Schema(description="유저 암호", example = "1234", required = true)
    private String password;

    public UserLoginResponseDTO(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


}
