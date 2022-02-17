package com.jdai.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueueMessage(String msg){
//        System.out.println("spring: 消费者接收到消息：【" + msg +"】");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueueMessage1(String msg) throws InterruptedException {
        System.out.println("spring: 消费者1......接收到消息：【" + msg +"】"+ LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueueMessage2(String msg) throws InterruptedException {
        System.out.println("spring: 消费者2----接收到消息：【" + msg +"】"+ LocalTime.now());
        Thread.sleep(250);
    }

    //fanout queue
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanQueueMessage1(String msg) throws InterruptedException {
        System.out.println("spring: 消费者1......接收到消息 from fanout：【" + msg +"】"+ LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanQueueMessage2(String msg) throws InterruptedException {
        System.out.println("spring: 消费者2----接收到消息 from fanout：【" + msg +"】"+ LocalTime.now());
        Thread.sleep(250);
    }

}
