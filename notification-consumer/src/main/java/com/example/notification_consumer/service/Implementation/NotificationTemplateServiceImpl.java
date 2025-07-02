package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.NotificationTemplateDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.NotificationTemplateMapper;
import com.example.notification_consumer.model.NotificationChannel;
import com.example.notification_consumer.model.NotificationTemplate;
import com.example.notification_consumer.model.NotificationType;
import com.example.notification_consumer.repository.NotificationTemplateRepository;
import com.example.notification_consumer.service.NotificationTemplateService;
import com.example.notification_consumer.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final NotificationTemplateRepository repository;
    private final NotificationTemplateMapper mapper;
    private final NotificationChannelService notificationChannelService;
    private final NotificationTypeService notificationTypeService;


    @Override
    public NotificationTemplateDto create(NotificationTemplateDto dto) {

        NotificationChannel notificationChannel = notificationChannelService.findById(dto.getNotificationChannelId());

        NotificationType notificationType = notificationTypeService.findById(dto.getNotificationTypeId());

        NotificationTemplate newTemplate = mapper.toEntity(dto);

        newTemplate.setNotificationChannel(notificationChannel);
        newTemplate.setNotificationType(notificationType);

        NotificationTemplate savedTemplate = repository.save(newTemplate);
        return mapper.toDto(savedTemplate);
    }

    @Override
    public NotificationTemplateDto find(Long id) {
        NotificationTemplate template = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("NotificationTemplate not found with id: " + id));
        return mapper.toDto(template);
    }

    @Override
    public Page<NotificationTemplateDto> findAll(int page, int size, Sort.Direction direction, String sortBy) {
        Pageable pageable = PageRequest.of(page,size,direction,sortBy);
        Page<NotificationTemplate> pageResult = repository.findAll(pageable);
        return pageResult.map(mapper::toDto);
    }

    @Override
    public NotificationTemplateDto update(Long id, NotificationTemplateDto dto) {

        NotificationTemplate notificationTemplate = findById(id);

        mapper.updateEntityFromDto(dto, notificationTemplate);

        if (dto.getNotificationChannelId() != null) {
            NotificationChannel notificationChannel = notificationChannelService.findById(dto.getNotificationChannelId());
            notificationTemplate.setNotificationChannel(notificationChannel);
        }

        if (dto.getNotificationTypeId() != null) {
            NotificationType notificationType = notificationTypeService.findById(dto.getNotificationTypeId());
            notificationTemplate.setNotificationType(notificationType);
        }

        NotificationTemplate updated = repository.save(notificationTemplate);
        return mapper.toDto(updated);
    }

    @Override
    public DeleteResponseDto delete(Long id) {
       if (!repository.existsById(id)) {
           throw new NotFoundException("NotificationTemplate not found with id: " + id);
       }
       repository.deleteById(id);

       DeleteResponseDto dto = new DeleteResponseDto();
       dto.setId(id);
       dto.setMessage("NotificationTemplate with id: " + id + "was deleted successfully");

       return dto;
    }

    public NotificationTemplate findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("NotificationTemplate not found with id: " + id));
    }
}
