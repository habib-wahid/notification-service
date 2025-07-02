package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findAllByUserId(Long userId);

}
