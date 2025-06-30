package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.UserDeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceTokenRepository extends JpaRepository<UserDeviceToken, Long> {
}
