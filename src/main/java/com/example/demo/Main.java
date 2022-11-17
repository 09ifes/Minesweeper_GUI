package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application{
    static Data data = new Data();
    @Override
   public void start(Stage stage) throws IOException{
        int rows = 10;
        int columns = 10;
        stage.setTitle("Minesweeper");
        Pane layout = new Pane();
        Scene scene = new Scene(layout, 500, 500);
        Grid grid = new Grid(columns, rows);



        for (int row = 0; row < grid.height; row++){
            int n = 0;
            for (int column = 0; column < grid.width; column++){
                n++;
                Cells cell = grid.cells[row][column].get(0);
                cell.setMinSize(22, 22);
                cell.setText(cell.appearance);
                cell.setOnAction(value ->  {
                    cell.setText("1");
                });
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        MouseButton button = mouseEvent.getButton();
                        if (button == MouseButton.PRIMARY) {
                            cell.setText("1");
                        }
                        else {
                            cell.setText("2");
                        }
                    }
                });
                layout.getChildren().add(cell);
            }
        }


        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // do your GUI stuff here
            }
        });





    }
}





















































