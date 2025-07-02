package com.example.notification_consumer.controller;

import com.example.notification_consumer.dto.ContactDto;
import com.example.notification_consumer.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDto> createContact(@RequestBody ContactDto contactDto) {
        ContactDto contactDtoResponse = contactService.create(contactDto);
        return ResponseEntity.ok(contactDtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> findContact(@PathVariable Long id) {
        ContactDto contact = contactService.find(id);
        return ResponseEntity.ok(contact);
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> findAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Page<ContactDto> contacts = contactService.findAll(page, size, direction, sortBy);
        return ResponseEntity.ok(contacts);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContactDto> updateContact(@PathVariable Long id, @RequestBody ContactDto contactDto) {
        ContactDto updated = contactService.update(id, contactDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
