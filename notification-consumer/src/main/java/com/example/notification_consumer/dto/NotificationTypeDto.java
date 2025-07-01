package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationTypeDto extends BaseDto{
    private String name;
    private String description;
}
