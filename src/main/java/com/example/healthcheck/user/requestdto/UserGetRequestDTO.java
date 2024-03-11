package com.example.healthcheck.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class UserGetRequestDTO {
    @Schema(description="유저 ID", example = "1", required = true)
    private UUID id;

    public UserGetRequestDTO(UUID id) {
        this.id = id;
    }

    public UserGetRequestDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
