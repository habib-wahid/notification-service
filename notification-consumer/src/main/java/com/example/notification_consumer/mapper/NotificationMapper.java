package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.NotificationDto;
import com.example.notification_consumer.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

     NotificationDto toDto(Notification notification);
     Notification toEntity(NotificationDto notificationDto);
     void update(NotificationDto notificationDto, @MappingTarget Notification notification);
}
