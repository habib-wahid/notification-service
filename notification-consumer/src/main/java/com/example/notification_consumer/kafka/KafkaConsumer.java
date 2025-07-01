package com.example.notification_consumer.kafka;

import com.example.notification_consumer.model.NotificationEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "general-user-notification", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String event) {
        System.out.println("Received event: " + event);
        try {
            NotificationEvent notificationEvent = objectMapper.readValue(event, NotificationEvent.class);
           // NotificationProcess.proceses(notificationEvent)
            System.out.println(notificationEvent.getNotificationType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
