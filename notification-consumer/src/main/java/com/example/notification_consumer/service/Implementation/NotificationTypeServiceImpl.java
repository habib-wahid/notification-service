package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationTypeDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.NotificationTypeMapper;
import com.example.notification_consumer.model.NotificationType;
import com.example.notification_consumer.repository.NotificationTypeRepository;
import com.example.notification_consumer.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private final NotificationTypeRepository repository;
    private final NotificationTypeMapper mapper;

    @Override
    public NotificationTypeDto create(NotificationTypeDto dto) {
        NotificationType newType = mapper.toEntity(dto);
        NotificationType savedType = repository.save(newType);
        return mapper.toDto(savedType);
    }

    @Override
    public NotificationTypeDto find(Long id) {
        NotificationType notificationType = findById(id);
        return mapper.toDto(notificationType);
    }

    @Override
    public Page<NotificationTypeDto> findAll(int page, int size, Sort.Direction direction, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<NotificationType> pageResult = repository.findAll(pageable);
        return pageResult.map(mapper::toDto);
    }

    @Override
    public NotificationType findByName(String notificationType) {
        return repository.findByName(notificationType)
                .orElseThrow(() -> new NotFoundException("NotificationType not found with name: " + notificationType));
    }

    @Override
    public NotificationTypeDto update(Long id, NotificationTypeDto dto) {
        NotificationType notificationType = findById(id);
        mapper.updateEntityFromDto(dto, notificationType);
        NotificationType updated = repository.save(notificationType);
        return mapper.toDto(updated);
    }

    @Override
    public DeleteResponseDto delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("NotificationType not found with id: " + id);
        }
        repository.deleteById(id);
        DeleteResponseDto dto = new DeleteResponseDto();
        dto.setId(id);
        dto.setMessage("NotificationType with id: " + id + "was deleted successfully");
        return dto;
    }

    @Override
    public NotificationType findById(Long notificationTypeId) {
        return repository.findById(notificationTypeId).orElseThrow(() -> new NotFoundException("NotificationType not found with id: " + notificationTypeId));
    }
}
