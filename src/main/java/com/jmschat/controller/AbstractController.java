package com.jmschat.controller;

import javafx.scene.Node;

/**
 * Created by gladi on 05.05.2017.
 */
public abstract class AbstractController implements Controller{
    private Node view;

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void setView(Node view) {
        this.view = view;
    }
}
