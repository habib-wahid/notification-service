package com.example.notification_consumer.controller;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationDto;
import com.example.notification_consumer.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> findNotificationById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(notificationService.find(id));
    }

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        return ResponseEntity.ok(notificationService.create(notificationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDto> deleteNotification(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(notificationService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<NotificationDto>> findAll(
            @RequestParam(name = "page_number", defaultValue = "0") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int size,
            @RequestParam(name = "direction", defaultValue = "desc") Sort.Direction direction,
            @RequestParam(name = "sort_by", defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(notificationService.findAll(page, size, String.valueOf(direction), sortBy));
    }
}
