package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto extends BaseDto {
    private String phoneNumber;
    private Long userId;
}
