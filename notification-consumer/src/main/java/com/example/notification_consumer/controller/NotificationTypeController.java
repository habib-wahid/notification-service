package com.example.notification_consumer.controller;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationTypeDto;
import com.example.notification_consumer.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification-types")
@RequiredArgsConstructor
public class NotificationTypeController {

    private final NotificationTypeService notificationTypeService;

    @PostMapping
    public ResponseEntity<NotificationTypeDto> createNotificationType(@RequestBody NotificationTypeDto dto){
        return ResponseEntity.ok(notificationTypeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationTypeDto> findNotificationTypeById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(notificationTypeService.find(id));
    }

    @GetMapping
    public ResponseEntity<Page<NotificationTypeDto>> findAllNotificationType(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return ResponseEntity.ok(notificationTypeService.findAll(page, size, direction, sortBy));
    }

    @PatchMapping("/id")
    public ResponseEntity<NotificationTypeDto> updateNotificationType(@PathVariable(value = "id") Long id, @RequestBody NotificationTypeDto dto){
        return ResponseEntity.ok(notificationTypeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<DeleteResponseDto> deleteNotificationType(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(notificationTypeService.delete(id));
    }



}
