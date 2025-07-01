package com.example.notification_consumer.service.Implementation;

import com.example.notification_consumer.dto.UserDeviceTokenDto;
import com.example.notification_consumer.exception.NotFoundException;
import com.example.notification_consumer.mapper.UserDeviceTokenMapper;
import com.example.notification_consumer.model.User;
import com.example.notification_consumer.model.UserDeviceToken;
import com.example.notification_consumer.repository.UserDeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDeviceTokenService {

    private final UserDeviceTokenRepository userDeviceTokenRepository;
    private final UserService userService;
    private final UserDeviceTokenMapper userDeviceTokenMapper;

    public UserDeviceTokenDto registerToken(UserDeviceTokenDto userDeviceTokenDto) {
        User user = userService.findUserById(userDeviceTokenDto.getUserId());
        UserDeviceToken userDeviceToken = userDeviceTokenMapper.toEntity(userDeviceTokenDto);
        userDeviceToken.setUser(user);
        userDeviceToken.setStatus(true);
        return userDeviceTokenMapper.toDto(userDeviceTokenRepository.save(userDeviceToken));
    }

    public UserDeviceTokenDto find(Long tokenId) {
        UserDeviceToken token = userDeviceTokenRepository.findById(tokenId)
                .orElseThrow(() -> new NotFoundException("Token not found"));
        return userDeviceTokenMapper.toDto(token);
    }

    public UserDeviceToken findById(Long tokenId) {
        return userDeviceTokenRepository.findById(tokenId)
                .orElseThrow(() -> new NotFoundException("Token not found"));
    }

    public UserDeviceTokenDto deactivateToken(Long tokenId) {
        UserDeviceToken token = userDeviceTokenRepository.findById(tokenId).orElseThrow(() -> new NotFoundException("Token not found"));
        token.setStatus(false);
        return userDeviceTokenMapper.toDto(userDeviceTokenRepository.save(token));
    }

    public List<UserDeviceTokenDto> getTokensByUser(Long userId) {
        return userDeviceTokenRepository.findAllByUserId(userId).stream()
                .map(userDeviceTokenMapper::toDto).toList();
    }

    public UserDeviceTokenDto delete(Long tokenId) {
        UserDeviceToken token = userDeviceTokenRepository.findById(tokenId)
                .orElseThrow(() -> new NotFoundException("Token not found"));
        userDeviceTokenRepository.delete(token);
        return userDeviceTokenMapper.toDto(token);
    }

}
