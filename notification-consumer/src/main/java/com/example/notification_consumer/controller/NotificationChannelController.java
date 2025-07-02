package com.example.notification_consumer.controller;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationChannelDto;
import com.example.notification_consumer.service.Implementation.NotificationChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification-channels")
@RequiredArgsConstructor
public class NotificationChannelController {

    private final NotificationChannelService notificationChannelService;


    @GetMapping("/{id}")
    public ResponseEntity<NotificationChannelDto> findChannelById(@PathVariable(value = "id")Long id) {
        return ResponseEntity.ok(notificationChannelService.find(id));
    }

    @PostMapping
    public ResponseEntity<NotificationChannelDto> createNotificationChannel(@RequestBody NotificationChannelDto notificationChannelDto) {
        return ResponseEntity.ok(notificationChannelService.create(notificationChannelDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NotificationChannelDto> updateNotificationChannel(
            @PathVariable(value = "id") Long id,
            @RequestBody NotificationChannelDto notificationChannelDto
    ) {
        return ResponseEntity.ok(notificationChannelService.update(id, notificationChannelDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDto> deleteNotificationChannel(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(notificationChannelService.delete(id));
    }


    @GetMapping
    public ResponseEntity<Page<NotificationChannelDto>> findAll(
            @RequestParam(name = "page_number", defaultValue = "0") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int size,
            @RequestParam(name = "direction", defaultValue = "desc") Sort.Direction direction,
            @RequestParam(name = "sort_by", defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(notificationChannelService.findAll(page, size, direction, sortBy));
    }




}
