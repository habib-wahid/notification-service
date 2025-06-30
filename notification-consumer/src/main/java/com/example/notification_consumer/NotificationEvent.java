package com.example.notification_consumer;

public class NotificationEvent {
    private Long userId;
    private String notificationType;

    public NotificationEvent() {

    }
    public NotificationEvent(Long userId, String notificationType) {
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
