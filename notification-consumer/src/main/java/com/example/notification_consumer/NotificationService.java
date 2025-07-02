package com.example.notification_consumer;

import com.example.notification_consumer.model.NotificationEvent;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(NotificationEvent notificationEvent) {
        System.out.println("Current Thread " + Thread.currentThread().getName() + " sending notification " + notificationEvent.toString());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
