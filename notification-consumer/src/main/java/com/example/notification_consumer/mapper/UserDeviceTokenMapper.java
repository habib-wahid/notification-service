package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.UserDeviceTokenDto;
import com.example.notification_consumer.model.UserDeviceToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDeviceTokenMapper {
     UserDeviceTokenDto toDto(UserDeviceToken userDeviceToken);

     UserDeviceToken toEntity(UserDeviceTokenDto userDeviceTokenDto);
}
