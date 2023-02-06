package com.kafka.myProducer.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    // create a topic
    @Bean
    public NewTopic myTopic() {
        return TopicBuilder.name("inventory_app").partitions(3).replicas(1).build();
    }
}
