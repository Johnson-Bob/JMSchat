package com.jmschat.controller;

import javafx.scene.Node;

/**
 * Created by gladi on 05.05.2017.
 */
public interface Controller {
    Node getView();
    void setView(Node view);
}
