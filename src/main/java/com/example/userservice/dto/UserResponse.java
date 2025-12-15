package com.example.userservice.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        Integer age,
        String gender,
        String bio,
        Integer score,
        String location
) {}
