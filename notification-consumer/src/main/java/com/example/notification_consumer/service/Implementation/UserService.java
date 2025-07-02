package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.UserDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.UserMapper;
import com.example.notification_consumer.model.Contact;
import com.example.notification_consumer.model.Email;
import com.example.notification_consumer.model.User;
import com.example.notification_consumer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto find(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        List<Email> emails = new ArrayList<>();
        if (userDto.getEmailList() != null) {
            emails = userDto.getEmailList().stream()
                    .map(emailAddress -> {
                        Email email = new Email();
                        email.setEmailAddress(emailAddress);
                        email.setUser(user);
                        return email;
                    })
                    .collect(Collectors.toList());
        }
        user.setEmails(emails);


        List<Contact> contacts = new ArrayList<>();
        if (userDto.getContactList() != null) {
            contacts = userDto.getContactList().stream()
                    .map(phoneNumber -> {
                        Contact contact = new Contact();
                        contact.setPhoneNumber(phoneNumber);
                        contact.setUser(user);
                        return contact;
                    })
                    .collect(Collectors.toList());
        }
        user.setContacts(contacts);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public Boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

}
