package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.DeleteResponseDto;
import com.example.notification_consumer.dto.UserPreferenceDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.UserPreferenceMapper;
import com.example.notification_consumer.model.NotificationChannel;
import com.example.notification_consumer.model.User;
import com.example.notification_consumer.model.UserPreference;
import com.example.notification_consumer.repository.UserPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceService {

    private final UserPreferenceRepository userPreferenceRepository;
    private final UserPreferenceMapper userPreferenceMapper;
    private final UserService userService;
    private final NotificationChannelService notificationChannelService;

    public UserPreference findById(Long id) {
        return userPreferenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UserPreference not found with id: " + id));
    }

    public UserPreferenceDto create(UserPreferenceDto dto) {
        User user = userService.findById(dto.getUserId());
        NotificationChannel channel = notificationChannelService.findById(dto.getChannelId());
        UserPreference userPreference = userPreferenceMapper.toEntity(dto);
        userPreference.setUser(user);
        userPreference.setChannel(channel);
        return userPreferenceMapper.toDto(userPreferenceRepository.save(userPreference));
    }

    public UserPreferenceDto find(Long id) {
        UserPreference userPreference = findById(id);
        return userPreferenceMapper.toDto(userPreference);
    }

    public DeleteResponseDto delete(Long id) {
        UserPreference userPreference = findById(id);
        userPreferenceRepository.delete(userPreference);
        return new DeleteResponseDto(id, "UserPreference deleted successfully");
    }

    public UserPreferenceDto update(Long id, UserPreferenceDto dto) {
        UserPreference userPreference = findById(id);
        User user = userService.findById(dto.getUserId());
        NotificationChannel channel = notificationChannelService.findById(dto.getChannelId());
        userPreference.setUser(user);
        userPreference.setChannel(channel);
        UserPreference updatedUserPreference = userPreferenceRepository.save(userPreference);
        return userPreferenceMapper.toDto(updatedUserPreference);
    }

   public Page<UserPreferenceDto> findAll(int page, int size, String sortBy, String direction) {
       Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
       Pageable pageable = PageRequest.of(page, size, sort);
       Page<UserPreference> userPreferences = userPreferenceRepository.findAll(pageable);
       return userPreferences.map(userPreferenceMapper::toDto);
   }


}
