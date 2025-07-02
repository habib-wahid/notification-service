package com.example.notification_consumer.kafka;

import com.example.notification_consumer.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.example.notification_consumer.model.NotificationEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final ExecutorService executorService;
    private final NotificationService notificationService;

    public KafkaConsumer(ObjectMapper objectMapper, ExecutorService executorService, NotificationService notificationService) {
        this.objectMapper = objectMapper;
        this.executorService = executorService;
        this.notificationService = notificationService;
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
            System.out.println(notificationEvent.getNotificationType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "notification-bulk-topic", containerFactory = "kafkaListenerContainerFactory")
    public void batchConsumer(List<ConsumerRecord<String, String>> records) throws JsonProcessingException {
        System.out.println("Received records: " + records.size());

        for (ConsumerRecord<String, String> record : records) {
            NotificationEvent notificationEvent = objectMapper.readValue(record.value(), NotificationEvent.class);
            //executorService.submit(() -> notificationService.sendNotification(notificationEvent));
        }
    }
}
