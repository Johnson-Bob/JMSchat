package com.jmschat;

import com.jmschat.controller.ChatController;
import com.jmschat.jms.JmsConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ChatController chatController = (ChatController) SpringFXMLLoader.load("/chat.fxml");
        primaryStage.setTitle("Chat room");
        Scene scene = new Scene((Parent) chatController.getView());
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop(){
        SpringFXMLLoader.CONTEXT.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
