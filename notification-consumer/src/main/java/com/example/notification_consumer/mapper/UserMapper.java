package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.UserDto;
import com.example.notification_consumer.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "emails", expression = "java(user.getEmails().stream().map(Email::getEmailAddress).toList())")
    @Mapping(target = "contacts", expression = "java(user.getContacts().stream().map(Contact::getPhoneNumber).toList())")
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
