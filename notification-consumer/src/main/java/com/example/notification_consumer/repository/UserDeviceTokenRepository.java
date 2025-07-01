package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.UserDeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDeviceTokenRepository extends JpaRepository<UserDeviceToken, Long> {
    List<UserDeviceToken> findAllByUserId(Long userId);
    List<UserDeviceToken> findAllByPlatform(String platform);
    List<UserDeviceToken> findAllByStatus(Boolean status);
}
