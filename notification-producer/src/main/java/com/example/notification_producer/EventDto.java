package com.example.notification_producer;

public class EventDto {
    private Long userId;
    private String notificationType;

    public EventDto(Long userId, String notificationType) {
        this.userId = userId;
        this.notificationType = notificationType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

}
