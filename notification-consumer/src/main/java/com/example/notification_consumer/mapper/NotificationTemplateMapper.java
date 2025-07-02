package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.NotificationTemplateDto;
import com.example.notification_consumer.model.NotificationTemplate;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NotificationTemplateMapper {

    @Mapping(source = "notificationType.id",target = "notificationTypeId")
    @Mapping(source = "notificationChannel.id",target = "notificationChannelId")
    NotificationTemplateDto toDto(NotificationTemplate entity);

    NotificationTemplate toEntity(NotificationTemplateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NotificationTemplate updateEntityFromDto(NotificationTemplateDto dto, @MappingTarget NotificationTemplate entity);
}
