package com.kafka.MyProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class MyProducerApplication {

    Logger logger = LoggerFactory.getLogger(MyProducerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyProducerApplication.class, args);
    }


    @Bean
    CommandLineRunner myCommandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        // to send messages using kafka template

        logger.info("in myCommandLineRunner");

        // topic to publish messages to
        String topic = "inventory_app";
//        String key = "item";
        String key = "location";

        // different types of messages data that can be sent
        // a csv format text
//        String data_csv = "Blue Jeans,Apparel,HSN001,test status,100,t,t,t";
        String data_csv =
                "111," +
                "Reliance & Co.,"+
                "Inventory Hub,"+
                "false,"+
                "true,"+
                "false,"+
                "addr line 1,"+
                "addr line 2,"+
                "addr line 3,"+
                "Kolkata,"+
                "West Bengal,"+
                "India,"+
                "700001";


        return args -> {
            int i = 250;

            while (i++ < 280) {

                logger.info("sending message:-"
                        + "\ntopic: " + topic
                        + "\nkey: " + key
                        + "\ndata: {" + i + "," + data_csv + "}");
                kafkaTemplate.send(topic,0, key, i + "," + data_csv);
//                kafkaTemplate.send(topic, key , i + "," + "test not item");
            }
        };
    }
}
