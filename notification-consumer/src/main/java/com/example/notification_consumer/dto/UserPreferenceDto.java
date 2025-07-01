package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPreferenceDto extends BaseDto{
    private Long userId;
    private Long channelId;
}
