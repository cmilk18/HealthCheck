package com.example.healthcheck.user.repository;

import com.example.healthcheck.user.domain.User;
import com.example.healthcheck.user.responsedto.UserLoginResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User,Long> {
    UserLoginResponseDTO findByName(String name);

    User findById(UUID userId);

    void deleteById(UUID userId);
}
