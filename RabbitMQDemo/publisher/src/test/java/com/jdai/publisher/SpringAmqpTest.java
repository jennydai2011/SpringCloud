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

//    @Test
//    public void testSimpleQueue(){
//        String queueName = "simple.queue";
//        String message = " hello, spring ampq 2!";
//        rabbitTemplate.convertAndSend(queueName, message);
//    }

    @Test
    public void testSendMessageWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = " hello, spring ampq work queue__";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }

    }

    @Test
    public void testSendMessagefanQueue() throws InterruptedException {
        //交换机名称
        String exchangeName = "mq.fanout";
        //消息
        String message = " hello, spring ampq fanout queue__";
        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend(exchangeName, "",message + i);
            Thread.sleep(20);
        }

    }

    @Test
    public void testSendMessageDirectQueue() throws InterruptedException {
        //交换机名称
        String exchangeName = "mq.direct";
        //消息
        String message1 = " hello, spring ampq direct queue__1";
        rabbitTemplate.convertAndSend(exchangeName, "blue",message1);

        String message2 = " hello, spring ampq direct queue__2";
        rabbitTemplate.convertAndSend(exchangeName, "red",message2);

        String message3 = " hello, spring ampq direct queue__3";
        rabbitTemplate.convertAndSend(exchangeName, "yellow",message3);

    }

    @Test
    public void testSendMessageTopicQueue() throws InterruptedException {
        //交换机名称
        String exchangeName = "mq.topic";
        //消息
        String message1 = " hello, spring ampq topic queue__1";
        rabbitTemplate.convertAndSend(exchangeName, "china.weather",message1);

        String message2 = " hello, spring ampq topic queue__2";
        rabbitTemplate.convertAndSend(exchangeName, "japan.news",message2);

        String message3 = " hello, spring ampq topic queue__3";
        rabbitTemplate.convertAndSend(exchangeName, "china.news",message3);

    }
}
