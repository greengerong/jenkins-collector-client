package com.github.greengerong;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2017                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@Component
public class Receiver {

    @RabbitListener(queues = MessageQueueConfiguration.QUEUE_NAME)
    public void receiveMessage(Message<MQMessage> mqMessage) {
        final MQMessage message = mqMessage.getPayload();
        System.out.println("=======================");
        System.out.printf("Get message name=%s, date=%s%n", message.getName(), message.getDate());
    }
}
