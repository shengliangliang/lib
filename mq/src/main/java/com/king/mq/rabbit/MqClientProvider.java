package com.king.mq.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqClientProvider {


    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        // "guest"/"guest" by default, limited to localhost connections
        factory.setUsername("win10");
        factory.setPassword("123456");
        factory.setVirtualHost("win10");
        factory.setHost("192.168.56.1");
        factory.setPort(5672);
        //factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");

        Connection conn = null;
        Channel channel = null;
        try {
            conn = factory.newConnection();
            channel = conn.createChannel();

            String exchangeName = "user.exchange.direct";
            String routingKey = "routing.key.user";
            String queueName = "user.queue";

            channel.exchangeDeclare(exchangeName, "direct", true);

            String existQueueName = ""; //channel.exchangeDeclare("dd");
            if(existQueueName!=null&&"user.queue".equals(existQueueName)){
                channel.queueBind(existQueueName, exchangeName, routingKey);
            }else{

                channel.queueDeclare(queueName, true, false, false, null);
                channel.queueBind(queueName,exchangeName, routingKey);
            }

           /* AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queueName,true,);*/



            byte[] messageBodyBytes = "Hello, world!".getBytes();
            channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);



            /*channel.exchangeDeclare(exchangeName, "direct", true);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, routingKey);



            channel.exchangeDeclare(exchangeName, "direct", true);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);*/

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            try {
                channel.close();
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }


    }
}
