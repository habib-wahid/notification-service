package com.example.notification_consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto extends BaseDto {
    private String email;
    private Long userId;
}
