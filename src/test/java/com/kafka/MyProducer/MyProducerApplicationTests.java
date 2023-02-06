package com.kafka.MyProducer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyProducerApplicationTests {

	@Test
    void testGetItemJsonString_returnString(){
        // set up
        MyProducerApplication myProducerApplication = new MyProducerApplication();
        int id = 1;
        // execute
        Object actual = myProducerApplication.getItemJsonString(id);
        // assert
        Assertions.assertEquals("".getClass(), actual.getClass());
    }
}
