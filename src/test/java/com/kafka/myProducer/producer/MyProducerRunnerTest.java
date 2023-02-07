package com.kafka.myProducer.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.myProducer.producer.MyProducerRunner;
import com.kafka.myProducer.producer.exceptionHandling.InvalidIdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    public void beforeEach() {
        System.out.println("Starting test for MyProducerRunner class");
//        mapper = new ObjectMapper();
    }

    @Test
    public void test_getItemJsonString_returns_a_String() throws InvalidIdException {
        // setup
        // mock id = 1
        Integer mockId = 1;

        // execute
        Object expected = "".getClass();
        Object actual = MyProducerRunner.getItemJsonString(mockId).getClass();
        // assert
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName
            ("test if the correct exception is thrown or not when id is negative or positive")
    @Test
    public void test_getItemJsonString_id_negative() {
        // setup
        Integer negativeId = -1;
        Integer positiveId = 1;
        // execute
        // assert
        Assertions.assertThrows(InvalidIdException.class, () -> {
            MyProducerRunner.getItemJsonString(negativeId);
        }, "Should throw exception");
        Assertions.assertDoesNotThrow(() -> {
            MyProducerRunner.getItemJsonString(positiveId);
        }, "Should not throw exception");
    }

    @Test
    public void test_getLocationJsonString_returns_a_String() throws InvalidIdException {
        // setup
        // mock id = 1
        Integer mockId = 1;

        // execute
        Object expected = "".getClass();
        Object actual = MyProducerRunner.getLocationJsonString(mockId).getClass();
        // assert
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName
            ("test if the correct exception is throw when id is negative")
    @Test
    public void test_getLocationJsonString_id_negative() {
        // setup
        Integer negativeId = -1;
        Integer positiveId = 1;
        // execute
        // assert
        Assertions.assertThrows(InvalidIdException.class, () -> {
            MyProducerRunner.getLocationJsonString(negativeId);
        }, "Should throw exception");
        Assertions.assertDoesNotThrow(() -> {
            MyProducerRunner.getLocationJsonString(positiveId);
        }, "Should not throw exception");
    }

}
