package com.kafka.myProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.kafka.myProducer")
@SpringBootApplication
public class MyProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyProducerApplication.class, args);
    }

}
