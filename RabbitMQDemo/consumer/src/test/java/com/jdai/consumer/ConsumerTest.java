package com.jdai.consumer;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class ConsumerTest {

    @Test
    public void testReceiveMessage() throws IOException, TimeoutException {
        //1.建立连接
        ConnectionFactory factory = new ConnectionFactory();
        //1.1设置连接参数，分别是:主机名，端口号，VHOST， 用户名，密码
        factory.setHost("3.96.62.140"); //aws instance ip
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("jdai");
        factory.setPassword("123123");

        //1.2建立连接
        Connection connection = factory.newConnection();

        //2. 创建通道channel
        Channel channel = connection.createChannel();

        //3.创建队列
        String queueName = "simple.queue";
        //channel.queueDeclare(queueName, false, false, false, null);

        //4.订阅消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException{
                //5.处理消息
                String message = new String(body);
                System.out.println("回调函数，接收消息成功： 【" + message + " 】");
            }
        });

        System.out.println("等待接收消息。。。");

        //5.关闭通道和连接
//        channel.close();
//        connection.close();
    }

}
