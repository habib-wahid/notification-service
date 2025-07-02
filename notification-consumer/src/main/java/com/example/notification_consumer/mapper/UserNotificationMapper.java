package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.UserNotificationDto;
import com.example.notification_consumer.model.UserNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserNotificationMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "notificationId", source = "notification.id")
    @Mapping(target = "channelId", source = "channel.id")
    UserNotificationDto toDto(UserNotification userNotification);

    UserNotification toEntity(UserNotificationDto userNotificationDto);

}
