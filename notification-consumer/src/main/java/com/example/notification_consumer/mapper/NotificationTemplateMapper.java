package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.NotificationTemplateDto;
import com.example.notification_consumer.model.NotificationTemplate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface NotificationTemplateMapper {

    @Mapping(source = "notificationType.id",target = "notificationTypeId")
    @Mapping(source = "notificationChannel.id",target = "notificationChannelId")
    NotificationTemplateDto toDto(NotificationTemplate entity);

    NotificationTemplate toEntity(NotificationTemplateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NotificationTemplate updateEntityFromDto(NotificationTemplateDto dto, NotificationTemplate entity);
}
