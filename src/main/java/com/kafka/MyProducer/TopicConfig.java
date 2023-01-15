package com.kafka.MyProducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    // create a topic
    @Bean
    public NewTopic myTopic() {
        return TopicBuilder.name("type_2_myTopic").build();
    }
}
