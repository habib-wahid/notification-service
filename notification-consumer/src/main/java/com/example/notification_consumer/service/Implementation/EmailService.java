package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.model.Email;
import com.example.notification_consumer.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    public Email findById(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Email not found with id: " + id));
    }

    public List<Email> findAllByUserId(Long userId) {
        return emailRepository.findAllByUserId(userId);
    }

}
