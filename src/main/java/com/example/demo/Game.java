package com.example.demo;

import javafx.scene.Node;

import java.util.Scanner;

public class Game {
    Data data;

    public Game(Data data) {
        this.data = data;

        this.updateUI();



    }

    public void updateUI(){
        Node cell = this.data.layout.getChildren().get(1);
        this.data.stage.show();


    }

}