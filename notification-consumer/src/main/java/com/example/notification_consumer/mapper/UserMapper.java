package com.example.notification_consumer.mapper;

import com.example.notification_consumer.dto.UserDto;
import com.example.notification_consumer.model.Contact;
import com.example.notification_consumer.model.Email;
import com.example.notification_consumer.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "emailList", expression = "java(toEmailList(user))")
    @Mapping(target = "contactList", expression = "java(toContactList(user))")
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    default List<String> toEmailList(User user) {
        return user.getEmails().stream().map(Email::getEmailAddress).toList();
    }

    default List<String> toContactList(User user) {
        return user.getContacts().stream().map(Contact::getPhoneNumber).toList();
    }

}
