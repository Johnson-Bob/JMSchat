package com.jmschat.jms;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

/**
 * Created by gladi on 01.05.2017.
 */
@Configuration
@ComponentScan(basePackages = "com.jmschat")
public class JmsConfig {
    public static final String BROKER_URL = "tcp://localhost:61616";
    public static final String SERVER_TOPIC = "severTopic";

    @Autowired
    MessageReceiver messageReceiver;

    @Bean
    ConnectionFactory connectionFactory(){
        return new SingleConnectionFactory(new ActiveMQConnectionFactory(BROKER_URL));
    }

    @Bean
    Topic topic(){
        return new ActiveMQTopic(SERVER_TOPIC);
    }

    @Bean
    MessageListenerContainer container(){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestination(topic());
        container.setMessageListener(messageReceiver);
        return container;
    }

    @Bean
    JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestination(topic());
        return jmsTemplate;
    }
}
