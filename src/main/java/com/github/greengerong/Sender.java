package com.github.greengerong;

import java.util.Date;

import static com.github.greengerong.MessageQueueConfiguration.EXCHANGE;
import static com.github.greengerong.MessageQueueConfiguration.SENDER_ROUTE_KEY;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public ResponseEntity<MQMessage> send() {
        final MQMessage mqMessage = getMqMessage();
        final String json = toJson(mqMessage);
        final Message message = MessageBuilder
                .withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        this.rabbitTemplate.convertAndSend(EXCHANGE, SENDER_ROUTE_KEY, message);
        return ResponseEntity.ok(mqMessage);
    }

    private String toJson(MQMessage mqMessage) {
        try {
            return new ObjectMapper().writeValueAsString(mqMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private MQMessage getMqMessage() {
        final MQMessage mqMessage = new MQMessage();
        mqMessage.setDate(new Date().getTime());
        mqMessage.setName("greengerong");
        return mqMessage;
    }
}
