package com.github.greengerong;

import static com.github.greengerong.MessageQueueConfiguration.EXCHANGE;
import static com.github.greengerong.MessageQueueConfiguration.LISTENER_ROUTE_KEY;
import static com.github.greengerong.MessageQueueConfiguration.QUEUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE, durable = "true"),
            exchange = @Exchange(value = EXCHANGE, durable = "true"),
            key = LISTENER_ROUTE_KEY
    ))
    public void receiveMessage(Message<MQMessage> mqMessage) {
        final MQMessage message = mqMessage.getPayload();
        dump(message);
    }

    private void dump(MQMessage message) {
        try {
            final String json = new ObjectMapper().writeValueAsString(message);
            System.out.println("******************Got message****************");
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
