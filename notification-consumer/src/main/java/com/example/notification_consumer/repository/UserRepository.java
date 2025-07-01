package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
