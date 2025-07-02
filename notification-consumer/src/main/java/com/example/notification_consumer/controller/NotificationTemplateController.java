package com.example.notification_consumer.controller;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationTemplateDto;
import com.example.notification_consumer.service.NotificationTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification-templates")
@RequiredArgsConstructor
public class NotificationTemplateController {

    private final NotificationTemplateService templateService;

    @PostMapping
    public ResponseEntity<NotificationTemplateDto> createNotificationTemplate(@RequestBody NotificationTemplateDto dto) {
        return ResponseEntity.ok(templateService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationTemplateDto> findNotificationTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(templateService.find(id));
    }

    @GetMapping
    public ResponseEntity<Page<NotificationTemplateDto>> findAllNotificationTemplate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(templateService.findAll(page, size, direction, sortBy));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationTemplateDto> updateNotificationTemplate(@PathVariable Long id, @RequestBody NotificationTemplateDto dto) {
        return ResponseEntity.ok(templateService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDto> deleteNotificationTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(templateService.delete(id));
    }
}