package com.example.healthcheck.user.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    private String password;

    private boolean emlAuthYn;

    private String emlAuthCd;
    @Builder
    public User(UUID id, String name, String password, boolean emlAuthYn, String emlAuthCd) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.emlAuthYn = emlAuthYn;
        this.emlAuthCd = emlAuthCd;
    }



    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
