package com.jmschat;


import com.jmschat.controller.Controller;
import com.jmschat.jms.JmsConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


import java.io.IOException;
import java.io.InputStream;

//It helps to make friends JavaFX and Spring
public class SpringFXMLLoader {
    public static final AbstractApplicationContext CONTEXT = new AnnotationConfigApplicationContext(JmsConfig.class);

    public static Controller load(String url){
        Controller controller = null;
        try (InputStream fxmlStream = SpringFXMLLoader.class.getResourceAsStream(url)){
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aClass) {
                    return CONTEXT.getBean(aClass);
                }
            });

            Node view = (Node) loader.load(fxmlStream);
            controller = loader.getController();
            controller.setView(view);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return controller;
    }

}
