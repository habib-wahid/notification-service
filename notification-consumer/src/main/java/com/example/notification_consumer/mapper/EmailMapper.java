package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.EmailDto;
import com.example.notification_consumer.dto.UserDto;
import com.example.notification_consumer.model.Email;
import com.example.notification_consumer.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    @Mapping(source = "user.id", target = "userId")
    EmailDto toDto(Email email);

    User toEntity(UserDto userDto);

}
