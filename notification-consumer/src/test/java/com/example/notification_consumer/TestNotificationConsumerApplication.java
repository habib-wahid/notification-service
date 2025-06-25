package com.example.notification_consumer;

import org.springframework.boot.SpringApplication;

public class TestNotificationConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.from(NotificationConsumerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
