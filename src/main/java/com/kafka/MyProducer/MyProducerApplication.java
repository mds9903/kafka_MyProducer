package com.kafka.MyProducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MyProducerApplication {

    Logger logger = LoggerFactory.getLogger(MyProducerApplication.class);

    // for json parsing
    ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(MyProducerApplication.class, args);
    }


    @Bean
    // this method sends messages using kafka template
    CommandLineRunner myCommandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        // topic to publish messages to
        String topic = "inventory_app";

        // a csv format text
        // String item_csv = "Blue Jeans,Apparel,HSN001,test status,100,t,t,t";
        // String location_csv = "Reliance & Co.,Inventory Hub,false,true,false,addr line 1,addr line 2,addr line 3,Kolkata,West Bengal,India,700001";

        return args -> {
            for (int i = 1202; ; i++) {
                // for every 1000th iteration
                if (i % 1000 == 0) {
                    try {
                        logger.info("SLEEPING FOR 5000 MS");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // Handle exception
                        logger.error("interrupted exception occurred\n\t" + e);
                    }
                }

                // a jsonString with unique id defined by i value
                final String msg = i % 2 == 0 ? getItemJsonString(i) : getLocationJsonString(i);

                logger.info("----sending message----" + "\ntopic: " + topic + "\nmsg:" + msg);
                kafkaTemplate.send(topic, msg);
            }
        };
    }

    // this method returns a string in a json format for 'items'
    public String getItemJsonString(int id){
        ObjectNode itemJson = mapper.createObjectNode();
        itemJson.put("itemId", id);
        itemJson.put("itemDesc", "a generic item created by kafka producer");
        itemJson.put("category", "kafka category");
        itemJson.put("itemType", "kafka type");
        itemJson.put("status", "unknown");
        itemJson.put("price", 99.99);
        itemJson.put("pickupAllowed", false);
        itemJson.put("shippingAllowed", false);
        itemJson.put("deliveryAllowed", false);
        return itemJson.toString();
    }

    // this method returns a string in a json format for 'items'
    public String getLocationJsonString(int id){
        ObjectNode locationJson = mapper.createObjectNode();
        locationJson.put("locationId", id);
        locationJson.put("locationDesc", "a generic location created by kafka producer");
        locationJson.put("type", "Kafka-Warehouse");
        locationJson.put("pickupAllowed", false);
        locationJson.put("shippingAllowed", false);
        locationJson.put("deliveryAllowed", false);
        locationJson.put("addrLine1", "kafka Line 1");
        locationJson.put("addrLine2", "kafka Line 2");
        locationJson.put("addrLine3", "kafka Line 3");
        locationJson.put("city", "kafka City");
        locationJson.put("state", "kafka State");
        locationJson.put("country", "kafka Country");
        locationJson.put("pincode", "456456");
        return locationJson.toString();
    }
}
