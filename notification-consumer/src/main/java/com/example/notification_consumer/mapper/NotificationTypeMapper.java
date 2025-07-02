package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.NotificationTypeDto;
import com.example.notification_consumer.model.NotificationType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface NotificationTypeMapper {

    NotificationTypeDto toDto(NotificationType entity);
    NotificationType toEntity(NotificationTypeDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(NotificationTypeDto dto, @MappingTarget NotificationType entity);
}
