package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.NotificationChannelDto;
import com.example.notification_consumer.model.NotificationChannel;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NotificationChannelMapper {

     NotificationChannelDto toDto(NotificationChannel notificationChannel);
     NotificationChannel toEntity(NotificationChannelDto notificationChannelDto);

     @InheritConfiguration(name = "toEntity")
     @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
     void updateEntityFromDto(NotificationChannelDto notificationChannelDto, @MappingTarget NotificationChannel notificationChannel);

}
