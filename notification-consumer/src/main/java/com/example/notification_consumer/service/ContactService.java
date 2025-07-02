package com.example.notification_consumer.service;

import com.example.notification_consumer.dto.ContactDto;
import com.example.notification_consumer.dto.DeleteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ContactService {
    ContactDto create(ContactDto contactDto);
    ContactDto find(Long id);
    Page<ContactDto> findAll(int page, int size, Sort.Direction direction, String sortBy);
    ContactDto update(Long id, ContactDto contactDto);
    DeleteResponseDto delete(Long id);
}
