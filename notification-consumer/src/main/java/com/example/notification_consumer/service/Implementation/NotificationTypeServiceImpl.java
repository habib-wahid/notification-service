package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.NotificationTypeMapper;
import com.example.notification_consumer.model.NotificationType;
import com.example.notification_consumer.repository.NotificationTypeRepository;
import com.example.notification_consumer.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private final NotificationTypeRepository repository;
    private final NotificationTypeMapper mapper;


    @Override
    public NotificationType findById(Long notificationTypeId) {
        return repository.findById(notificationTypeId).orElseThrow(()-> new NotFoundException("NotificationType not found with id: " + notificationTypeId));
    }
}
