package com.example.notification_consumer.controller;

import com.example.notification_consumer.dto.UserDeviceTokenDto;
import com.example.notification_consumer.service.Implementation.UserDeviceTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-device-tokens")
public class UserDeviceTokenController {

    private final UserDeviceTokenService userDeviceTokenService;

    public UserDeviceTokenController(UserDeviceTokenService userDeviceTokenService) {
        this.userDeviceTokenService = userDeviceTokenService;
    }

    @PostMapping
    public ResponseEntity<UserDeviceTokenDto> registerToken(@RequestBody UserDeviceTokenDto token) {
        UserDeviceTokenDto saved = userDeviceTokenService.registerToken(token);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{userId}")
    public List<UserDeviceTokenDto> getUserTokens(@PathVariable Long userId) {
        return userDeviceTokenService.getTokensByUser(userId);
    }

    @DeleteMapping("/{tokenId}")
    public ResponseEntity<UserDeviceTokenDto> delete(@PathVariable Long tokenId) {
        return ResponseEntity.ok(userDeviceTokenService.delete(tokenId));
    }

    @PutMapping("/deactivate/{tokenId}")
    public ResponseEntity<UserDeviceTokenDto> deactivate(@PathVariable Long tokenId) {
        return ResponseEntity.ok(userDeviceTokenService.deactivateToken(tokenId));
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity<UserDeviceTokenDto> find(@PathVariable Long tokenId) {
        return ResponseEntity.ok(userDeviceTokenService.find(tokenId));
    }


}
