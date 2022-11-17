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
import java.util.Objects;


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
            for (int column = 0; column < grid.width; column++){
                Cells cell = grid.cells[row][column].get(0);
                cell.setMinSize(30, 32);
                cell.setText(cell.appearance);
                layout.getChildren().add(cell);

                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        MouseButton button = mouseEvent.getButton();
                        if (button == MouseButton.PRIMARY) {
                            if (cell.isClickable) {
                                if (cell.isMine){
                                    cell.appearance = "✳";
                                    //grid.determineCell((cell.x / 35), (cell.y / 35));
                                    // reveal all mines
                                }
                                else if (cell.adjacentMinesCount > 0){
                                    cell.appearance = "" + cell.adjacentMinesCount;
                                    grid.numberedCell((cell.x / 35), (cell.y / 35));
                                }
                                else if (cell.adjacentMinesCount == 0){
                                    cell.appearance = "" + cell.adjacentMinesCount;
                                    grid.adjacentChecker((cell.x / 35), (cell.y / 35));


                                   // grid.blankCell((cell.x / 35), (cell.y / 35));
                                }


                                cell.setText(cell.appearance);
                               // grid.determineCell((cell.x / 35), (cell.y / 35) );
                            }

                        }
                        else { // right click
                            if (Objects.equals(cell.state, "unopened")){
                                cell.appearance = "\uD83D\uDEA9";
                                cell.setText(cell.appearance);
                                grid.placeFlag((cell.x / 35), (cell.y / 35));    // divide by 35 to make compatible with console coords
                            }
                            else if (Objects.equals(cell.state, "flagged")) {
                                cell.appearance = " ";
                                cell.setText(cell.appearance);
                                grid.placeFlag((cell.x / 35), (cell.y / 35));    // divide by 35 to make compatible with console coords
                            }
                        }


                    }
                    });




            }
        }


        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();




    }
}





















































