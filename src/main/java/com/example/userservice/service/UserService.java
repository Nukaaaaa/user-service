package com.example.userservice.service;
import com.example.userservice.dto.CreateUserRequest;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public User create(UUID userId, CreateUserRequest req) {
        User user = new User();
        user.setId(userId);
        user.setUsername(req.username());
        user.setAge(req.age());
        user.setGender(req.gender());
        user.setBio(req.bio());
        user.setLocation(req.location());
        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("User already exists");
        }
    }

    public User get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public void updateScore(UUID id, int delta) {
        repository.incrementScore(id, delta);
    }
}
