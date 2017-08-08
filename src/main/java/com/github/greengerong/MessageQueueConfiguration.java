package com.github.greengerong;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2017                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@Configuration
@EnableConfigurationProperties(MQSetting.class)
public class MessageQueueConfiguration implements RabbitListenerConfigurer {

    public final static String QUEUE = "mq-test";
    public final static String EXCHANGE = "com.github.greengerong.exchange";
    public final static String SENDER_ROUTE_KEY = "com.github.greengerong.event";
    public final static String LISTENER_ROUTE_KEY = "com.github.greengerong.event";

    private final MQSetting mqSetting;
    private final ConnectionFactory connectionFactory;

    @Autowired
    public MessageQueueConfiguration(MQSetting mqSetting, ConnectionFactory connectionFactory) {
        this.mqSetting = mqSetting;
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(mqSetting.getConcurrentConsumers());
        factory.setMaxConcurrentConsumers(mqSetting.getMaxConcurrentConsumers());
        return factory;
    }


    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(getHandlerMessageConverter());
        return factory;
    }

    @Bean
    public MappingJackson2MessageConverter getHandlerMessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public Jackson2JsonMessageConverter getTemplateMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }
}
