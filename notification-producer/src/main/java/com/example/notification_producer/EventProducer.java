package com.example.notification_producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EventProducer {

    private final List<Long> userIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L);
    private final List<String> notificationType = List.of("promotional", "new_year","ticket_creation", "promotional_ticket_creation", "new_service_add", "profile_modification");
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public EventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishEvent(String topic, EventDto eventDto) {

        try {
            String jsonStr = objectMapper.writeValueAsString(eventDto);
            kafkaTemplate.send(topic, jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void bulkPublishEvent(String topic) {
        for (int i = 0; i < 100; i++) {
            Integer idIndex = new Random().nextInt(20);
            int eventIndex = new Random().nextInt(6);

            EventDto eventDto = new EventDto(userIds.get(idIndex), notificationType.get(eventIndex));
            try{
                String eventJson = objectMapper.writeValueAsString(eventDto);
                kafkaTemplate.send(topic, notificationType.get(eventIndex), eventJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
