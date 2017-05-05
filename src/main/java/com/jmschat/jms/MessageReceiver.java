package com.jmschat.jms;

import com.jmschat.controller.ChatController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by gladi on 01.05.2017.
 */

@Component
public class MessageReceiver implements MessageListener {
    @Autowired
    ChatController chatController;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage){
            try {
                chatController.addText(((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else {
            throw new IllegalArgumentException("The message must be type of TextMessage");
        }
    }
}
