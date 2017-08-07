package com.github.greengerong;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2017                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@RestController("mq")
public class Sender {

    @Autowired
    private RabbitMessagingTemplate rabbitTemplate;
    @Autowired
    private MappingJackson2MessageConverter jackson2MessageConverter;

    @GetMapping
    public ResponseEntity<MQMessage> send() {
        this.rabbitTemplate.setMessageConverter(this.jackson2MessageConverter);
        final MQMessage mqMessage = getMqMessage();
        this.rabbitTemplate.convertAndSend(MessageQueueConfiguration.QUEUE_NAME, mqMessage);
        return ResponseEntity.ok(mqMessage);
    }

    private MQMessage getMqMessage() {
        final MQMessage mqMessage = new MQMessage();
        mqMessage.setDate(new Date().getTime());
        mqMessage.setName("greengerong");
        return mqMessage;
    }
}
