package com.kafka.MyProducer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MyProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProducerApplication.class, args);
	}

	// to send messages using kafka template
	@Bean
	CommandLineRunner myCommandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			int i = 0;
			while(i < 1000_000_000) {
				kafkaTemplate.send("type_2_myTopic", "for loop msg: #" + i++);
			}
		};
	}

}
