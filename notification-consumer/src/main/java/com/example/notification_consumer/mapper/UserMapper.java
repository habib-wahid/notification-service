package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.UserDto;
import com.example.notification_consumer.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
