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
        String topic = "items";

        // different types of messages data that can be sent
        // a csv format text
        String data_csv = "Blue Jeans,Apparel,HSN001,test status,100,t,t,t";

        List<String> data = new ArrayList<>();


        return args -> {
            int i = 250;
//            logger.info("sending message:-"
//                    + "\ntopic: " + topic
//                    + "\ndata: {" + i + "," + data_csv + "}");
//            kafkaTemplate.send(topic, i + "," + data_csv);
            while (i++ < 280) {
                logger.info("sending message:-"
                        + "\ntopic: " + topic
                        + "\ndata: {" + i + "," + data_csv + "}");
                kafkaTemplate.send(topic, i + "," + data_csv);
            }
        };
    }
    // a map of string-string
//		HashMap<String, String> data_map = new HashMap<>();
//		data_map.put("itemId", "123456789");
//		data_map.put("itemDesc", "test Desc");
//		data_map.put("itemType", "test Type");
//		data_map.put("itemStatus", "test Status");
//		data_map.put("itemPrice", "9.99");
//		data_map.put("pickupAllowed", "true");
//		data_map.put("shippingAllowed", "true");
//		data_map.put("deliveryAllowed", "true");
}
