package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class Main extends Application{


    @Override
   public void start(Stage stage) throws IOException{
        stage.setTitle("Minesweeper");
        Pane layout = new Pane();
        Grid grid = new Grid(500, 200);



        for (int row = 0; row < grid.height; row++){
            int n = 0;
            for (int column = 0; column < grid.width; column++){
                n++;
                Cells cell = grid.cells[row][column].get(0);
                cell.setMinSize(22, 22);
                layout.getChildren().add(cell);
            }

        }

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();

    } 


    public static void main(String[] args) {
        int x = 10;
        int y = 10;
        Game game = new Game(x, y);
        launch();

    }
}





















































