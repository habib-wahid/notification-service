package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.UserDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.UserMapper;
import com.example.notification_consumer.model.User;
import com.example.notification_consumer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    public UserDto saveUser(UserDto user) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }

    public Boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

}
