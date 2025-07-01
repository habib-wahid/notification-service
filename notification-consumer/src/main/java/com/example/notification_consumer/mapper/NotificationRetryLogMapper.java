package com.example.notification_consumer.mapper;


import com.example.notification_consumer.dto.NotificationRetryLogDto;
import com.example.notification_consumer.model.NotificationRetryLog;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface NotificationRetryLogMapper {

    @Mapping(source = "userNotification.id", target = "userNotificationId")
    NotificationRetryLogDto toDto(NotificationRetryLog entity);

    NotificationRetryLog toEntity(NotificationRetryLogDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NotificationRetryLog updateEntityFromDto(NotificationRetryLogDto dto, NotificationRetryLog entity);
}
