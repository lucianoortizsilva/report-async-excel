package com.report.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Bean
	Queue queue() {
		return new Queue("QUEUE-REPORT", false);
	}
	
}