package com.example.demo.service;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserRepository;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserInfo> getUserById(Integer userId) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        return userRepository.findById(userId);
    }

    public Iterable<UserInfo> findAll() {
        return userRepository.findAll();
    }

    public UserInfo createUser(UserInfo user) {

        if (user == null) {
            throw new IllegalArgumentException("User details cannot be empty");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getPNo() == null || user.getPNo().isBlank()) {
            throw new IllegalArgumentException("PNo is required");
        }

        if (user.getMobileNo() == null || user.getMobileNo().isBlank()) {
            throw new IllegalArgumentException("Mobile number is required");
        }

        return userRepository.save(user);
    }
}