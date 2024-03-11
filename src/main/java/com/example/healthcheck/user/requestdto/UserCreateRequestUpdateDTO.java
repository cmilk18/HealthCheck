package com.example.healthcheck.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserCreateRequestUpdateDTO {


    @Schema(description="유저 ID", example = "1", required = true)
    private UUID id;


    @Schema(description="유저 이름", example = "Kang", required = true)
    private String name;


    @Schema(description="유저 암호", example = "1234", required = true)
    private String password;

    @Schema(description="메일 인증 여부", example = "1234", required = true)
    private boolean emlAuthYn;

    @Schema(description="메일 인증 코드", example = "1234", required = true)
    private String emlAuthCd;


    public UserCreateRequestUpdateDTO() {
    }

    @Builder
    public UserCreateRequestUpdateDTO(UUID id, String name, String password, boolean emlAuthYn, String emlAuthCd) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.emlAuthYn = emlAuthYn;
        this.emlAuthCd = emlAuthCd;
    }
}
