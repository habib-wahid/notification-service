package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.UserNotificationDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.UserNotificationMapper;
import com.example.notification_consumer.model.Notification;
import com.example.notification_consumer.model.NotificationChannel;
import com.example.notification_consumer.model.User;
import com.example.notification_consumer.model.UserNotification;
import com.example.notification_consumer.repository.UserNotificationRepository;
import com.example.notification_consumer.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNotificationService {

    private final UserNotificationRepository userNotificationRepository;
    private final UserNotificationMapper userNotificationMapper;
    private final UserService userService;
    private final NotificationService notificationService;
    private final NotificationChannelService notificationChannelService;

    public UserNotification findById(Long id) {
        return userNotificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UserNotification not found with id: " + id));
    }

    public UserNotificationDto create(UserNotificationDto userNotificationDto) {
        User user = userService.findById(userNotificationDto.getUserId());
        Notification notification = notificationService.findById(userNotificationDto.getNotificationId());
        NotificationChannel notificationChannel = notificationChannelService.findById(userNotificationDto.getChannelId());

        UserNotification userNotification = userNotificationMapper.toEntity(userNotificationDto);
        userNotification.setUser(user);
        userNotification.setNotification(notification);
        userNotification.setChannel(notificationChannel);
        UserNotification savedUserNotification = userNotificationRepository.save(userNotification);
        return userNotificationMapper.toDto(savedUserNotification);
    }

    public UserNotificationDto find(Long id) {
        UserNotification userNotification = findById(id);
        return userNotificationMapper.toDto(userNotification);
    }

    public DeleteResponseDto delete(Long id) {
        UserNotification userNotification = findById(id);
        userNotificationRepository.delete(userNotification);
        return new DeleteResponseDto(id,"UserNotification deleted successfully");
    }
}
