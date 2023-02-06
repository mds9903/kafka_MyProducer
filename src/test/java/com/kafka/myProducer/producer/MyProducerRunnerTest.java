package com.kafka.myProducer.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.myProducer.producer.MyProducerRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import scala.Int;


//@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyProducerRunner.class)
public class MyProducerRunnerTest {
    @MockBean
    ObjectMapper mapper;

    @BeforeEach
    public void beforeEach(){
        System.out.println("Starting test for MyProducerRunner class");
//        mapper = new ObjectMapper();
    }

    @Test
    public void test_getItemJsonString_returns_a_String(){
        // setup
        // mock id = 1
        Integer mockId = 1;

        // execute
        Object expected = "".getClass();
        Object actual =  MyProducerRunner.getItemJsonString(mockId).getClass();
        // assert
        Assertions.assertEquals(expected,actual);
    }

//    @Test
//    public void emptyTest() {
//        Assertions.assertEquals("test", "test");
//    }
}
