package com.example.notification_consumer.controller;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationRetryLogDto;
import com.example.notification_consumer.model.NotificationRetryLog;
import com.example.notification_consumer.service.NotificationRetryLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification-retry-logs")
public class NotificationRetryLogController {

    private final NotificationRetryLogService retryLogService;

    @PostMapping
    public ResponseEntity<NotificationRetryLogDto> createNotificationRetryLog(@RequestBody NotificationRetryLogDto log) {
        return ResponseEntity.ok(retryLogService.create(log));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationRetryLogDto> findNotificationRetryLog(@PathVariable Long id) {
        return ResponseEntity.ok(retryLogService.find(id));
    }

    @GetMapping
    public ResponseEntity<Page<NotificationRetryLogDto>> findAllNotificationRetryLog(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<NotificationRetryLogDto> response = retryLogService.findAll(page, size, direction, sortBy);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NotificationRetryLogDto> updateNotificationRetryLog(@PathVariable Long id, @RequestBody NotificationRetryLogDto log) {
        return ResponseEntity.ok(retryLogService.update(id, log));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDto> deleteNotificationRetryLog(@PathVariable Long id) {
        DeleteResponseDto dto = retryLogService.delete(id);
        return ResponseEntity.ok(dto);
    }
}
