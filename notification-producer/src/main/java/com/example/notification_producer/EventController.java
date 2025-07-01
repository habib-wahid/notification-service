package com.example.notification_producer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventController {

    private final EventProducer eventProducer;

    public EventController(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/produce")
    public void produceEvent(@RequestBody EventDto eventDto) {
        eventProducer.publishEvent("general-user-notification", eventDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/bulk-produce")
    public void bulkProduceEvent() {
        eventProducer.bulkPublishEvent("notification-bulk-topic");
    }
}
