package com.jmschat.controller;

import com.jmschat.jms.MessageSender;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatController extends AbstractController{

    @Autowired
    MessageSender messageSender;

    @FXML private Text textName;
    @FXML private TextField name;
    @FXML private HBox bottomHBox;
    @FXML private Button btnName;
    @FXML private TextArea chat;
    @FXML private TextField message;

    @FXML protected void onClickOk(){
        if (name.getText().equals("")){
            return;
        }
        messageSender.sendMessage(name.getText() + " joined the chat");
        changeView();
    }

    @FXML protected void onClickSend(){
        if (message.getText().equals("")){
            return;
        }
        messageSender.sendMessage(name.getText() + ">> " + message.getText() );
        message.clear();
    }

    public void addText(String text){
        String newLine = System.getProperty("line.separator");
        chat.appendText(newLine);
        chat.appendText(text);
    }

    private void changeView(){
        bottomHBox.setVisible(true);
        name.setVisible(false);
        btnName.setVisible(false);
        textName.setText("You joined as " + name.getText());
    }
}
