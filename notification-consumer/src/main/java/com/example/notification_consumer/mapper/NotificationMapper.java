package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.NotificationDto;
import com.example.notification_consumer.model.Notification;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "typeId", source = "type.id")
    NotificationDto toDto(Notification notification);

    Notification toEntity(NotificationDto notificationDto);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(NotificationDto notificationDto, @MappingTarget Notification notification);
}
