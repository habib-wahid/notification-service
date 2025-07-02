package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationRetryLogDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.NotificationRetryLogMapper;
import com.example.notification_consumer.model.NotificationRetryLog;
import com.example.notification_consumer.model.UserNotification;
import com.example.notification_consumer.repository.NotificationRetryLogRepository;
import com.example.notification_consumer.service.NotificationRetryLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationRetryLogServiceImpl implements NotificationRetryLogService {

    private final UserNotificationService userNotificationService;
    private final NotificationRetryLogRepository repository;
    private final NotificationRetryLogMapper mapper;

    @Override
    public NotificationRetryLogDto create(NotificationRetryLogDto logDto) {
        UserNotification userNotification = userNotificationService.findById(logDto.getId());

        NotificationRetryLog newLog = mapper.toEntity(logDto);
        newLog.setUserNotification(userNotification);

        NotificationRetryLog savedLog = repository.save(newLog);
        return mapper.toDto(savedLog);
    }

    @Override
    public NotificationRetryLogDto find(Long id) {
        NotificationRetryLog retryLog = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("NotificationRetryLog not found with id: " + id));
        return mapper.toDto(retryLog);
    }

    @Override
    public Page<NotificationRetryLogDto> findAll(int page, int size, Sort.Direction direction, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
        Page<NotificationRetryLog> pageResult = repository.findAll(pageable);
        return pageResult.map(mapper::toDto);

    }

    @Override
    public NotificationRetryLogDto update(Long id, NotificationRetryLogDto logDto) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("NotificationRetryLog not found with id: " + id);
        }
        NotificationRetryLog entity = mapper.toEntity(logDto);
        entity.setId(id);
        NotificationRetryLog updated = repository.save(entity);
        return mapper.toDto(updated);
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("NotificationRetryLog not found with id: " + id);
        }
        repository.deleteById(id);

        DeleteResponseDto dto = new DeleteResponseDto();
        dto.setId(id);
        dto.setMessage("NotificationRetryLog with id: " + id + "was deleted successfully");

        return dto;
    }
}