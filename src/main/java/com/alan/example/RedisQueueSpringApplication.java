package com.alan.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("si-configuration.xml")
public class RedisQueueSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisQueueSpringApplication.class, args);
	}
}
