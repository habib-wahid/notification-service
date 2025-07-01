package com.example.notification_consumer.repository;

import com.example.notification_consumer.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Contact, Long> {
}
