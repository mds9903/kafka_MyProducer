package com.kafka.myProducer.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kafka.myProducer.app.MyProducerApplication;
import com.kafka.myProducer.producer.exceptionHandling.InvalidIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyProducerRunner {
    private final Logger logger = LoggerFactory.getLogger(MyProducerApplication.class);

    // for json parsing
    private static final ObjectMapper mapper = new ObjectMapper();

    @Value("${sleepPeriod}")
    // the sleep period in ms after pushing messages
    private Integer sleepPeriod;

    // this method sends messages using kafka template
    CommandLineRunner myCommandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        // topic to publish messages to
        String topic = "inventory_app";

        // a csv format text
        // String item_csv = "Blue Jeans,Apparel,HSN001,test status,100,t,t,t";
        // String location_csv = "Reliance & Co.,Inventory Hub,false,true,false,addr line 1,addr line 2,addr line 3,Kolkata,West Bengal,India,700001";

        return args -> {
            for (int i = 1202; i < 1205; i++) {
                // for every 1000th iteration
                if (i % 1000 == 0) {
                    try {
                        logger.info("SLEEPING FOR "+sleepPeriod);
                        Thread.sleep(sleepPeriod);
                    } catch (InterruptedException e) {
                        // Handle exception
                        logger.error("interrupted exception occurred\n\t" + e);
                    }
                }

                // a jsonString with unique id defined by i value
                final String msg;
                try {
                    msg = i % 2 == 0 ? getItemJsonString(i) : getLocationJsonString(i);
                } catch (InvalidIdException e) {
                    throw new RuntimeException(e);
                }

                logger.info("----sending message----" + "\ntopic: " + topic + "\nmsg:" + msg);
                kafkaTemplate.send(topic, msg);
            }
        };
    }

    // this method returns a string in a json format for 'items'
    public static String getItemJsonString(Integer id) throws InvalidIdException {
        if(id <= 0){
            throw new InvalidIdException("Given ID is not a valid positive integer");
        }
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
    public static String getLocationJsonString(Integer id) throws InvalidIdException {
        if(id <= 0){
            throw new InvalidIdException("Given ID is not a valid positive integer");
        }
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
