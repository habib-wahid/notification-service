package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.UserPreferenceDto;
import com.example.notification_consumer.model.UserPreference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPreferenceMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "channelId", source = "channel.id")
    UserPreferenceDto toDto(UserPreference userPreference);

    UserPreference toEntity(UserPreferenceDto userPreferenceDto);
}
