package com.example.demo.controller;

import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo> getUserById(
            @PathVariable Integer userId) {

        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfo> createUser(
            @RequestBody UserInfo user) {

        UserInfo savedUser = userService.createUser(user);

        return ResponseEntity.status(201).body(savedUser);
    }
}