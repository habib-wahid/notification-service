package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    List<UserPreference> findAllByUserId(Long userId);
}
