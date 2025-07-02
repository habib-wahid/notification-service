package com.example.notification_consumer.controller;

import com.example.notification_consumer.dto.UserDto;
import com.example.notification_consumer.service.Implementation.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.find(userId));
    }
    @PostMapping
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @GetMapping("/exists/{userId}")
    ResponseEntity<Boolean> userExists(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.userExists(userId));
    }
}
