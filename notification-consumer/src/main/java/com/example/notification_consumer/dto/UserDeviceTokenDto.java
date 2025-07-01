package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeviceTokenDto extends BaseDto{
    private Long userId;
    private String token;
    private String platform;
    private Boolean status;
    private Long appId;
}
