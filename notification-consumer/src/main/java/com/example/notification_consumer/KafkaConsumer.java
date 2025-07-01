package com.example.notification_consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
            System.out.println(notificationEvent.getNotificationType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "bulk-user-notification", groupId = "bulk-group")
    public void bulkConsume(String event) {
        System.out.println("Received event: " + event);
        try {
            NotificationEvent notificationEvent =
                    objectMapper.readValue(event, NotificationEvent.class);
            System.out.println(notificationEvent.getUserId() + " " + notificationEvent.getNotificationType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
