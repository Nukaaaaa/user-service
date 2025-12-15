package com.example.userservice.controller;

import com.example.userservice.dto.CreateUserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    // --------------------------
    // Create profile (lazy)
    // --------------------------
    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(
            @RequestHeader("X-User-Id") UUID userId,
            @Valid @RequestBody CreateUserRequest req
    ) {
        return map(service.create(userId, req));
    }

    // --------------------------
    // Get own profile
    // --------------------------
    @GetMapping("/me")
    public UserResponse me(@RequestHeader("X-User-Id") UUID userId) {
        return map(service.get(userId));
    }

    // --------------------------
    // Get any user by id
    // --------------------------
    @GetMapping("/{id}")
    public UserResponse get(@PathVariable UUID id) {
        return map(service.get(id));
    }

    // --------------------------
    // Mapping Entity -> DTO
    // --------------------------
    private UserResponse map(com.example.userservice.model.User u) {
        return new UserResponse(
                u.getId(),
                u.getUsername(),
                u.getAge(),
                u.getGender(),
                u.getBio(),
                u.getScore(),
                u.getLocation()
        );
    }
}
