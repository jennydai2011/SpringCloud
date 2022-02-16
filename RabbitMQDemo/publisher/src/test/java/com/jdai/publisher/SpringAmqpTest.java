package com.jdai.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue(){
        String queueName = "simple.queue";
        String message = " hello, spring ampq 2!";
        rabbitTemplate.convertAndSend(queueName, message);
    }
}