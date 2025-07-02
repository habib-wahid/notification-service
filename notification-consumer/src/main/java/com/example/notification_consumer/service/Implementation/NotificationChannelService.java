package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationChannelDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.NotificationChannelMapper;
import com.example.notification_consumer.model.NotificationChannel;
import com.example.notification_consumer.repository.NotificationChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationChannelService {

    private final NotificationChannelRepository notificationChannelRepository;
    private final NotificationChannelMapper notificationChannelMapper;

    public NotificationChannelDto find(Long id) {
        return notificationChannelRepository.findById(id)
                .map(notificationChannelMapper::toDto)
                .orElseThrow(() -> new NotFoundException("NotificationChannel not found with id: " + id));
    }

    public NotificationChannel findById(Long id) {
        return notificationChannelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NotificationChannel not found with id: " + id));
    }

    public NotificationChannelDto create(NotificationChannelDto notificationChannelDto) {
        NotificationChannel notificationChannel = notificationChannelMapper.toEntity(notificationChannelDto);
        var savedNotificationChannel = notificationChannelRepository.save(notificationChannel);
        return notificationChannelMapper.toDto(savedNotificationChannel);
    }

    public NotificationChannelDto update(Long id, NotificationChannelDto notificationChannelDto) {
        NotificationChannel channel = notificationChannelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NotificationChannel not found with id: " + id));

        notificationChannelMapper.updateEntityFromDto(notificationChannelDto, channel);

        notificationChannelRepository.save(channel);
        return notificationChannelMapper.toDto(channel);
    }

    public DeleteResponseDto delete(Long id) {
        NotificationChannel channel = notificationChannelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NotificationChannel not found with id: " + id));
        notificationChannelRepository.delete(channel);
        return new DeleteResponseDto(id, "NotificationChannel deleted successfully");
    }

    public Page<NotificationChannelDto> findAll(int page, int size, Sort.Direction direction, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return notificationChannelRepository.findAll(pageRequest)
                .map(notificationChannelMapper::toDto);
    }

}
