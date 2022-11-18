package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class Main extends Application{

    @Override
   public void start(Stage stage) throws IOException{
        stage.setTitle("Minesweeper");


        Label labelRows = new Label("Please enter the number of\n rows (1 - 20)");
        labelRows.setLayoutX(5);
        labelRows.setLayoutY(100);
        Label labelColumns = new Label("Please enter the number\n of columns (1 - 20)");
        labelColumns.setLayoutX(5);
        labelColumns.setLayoutY(150);

        TextField enterRows = new TextField();
        enterRows.setLayoutX(180);
        enterRows.setLayoutY(100);

        TextField enterColumns = new TextField();
        enterColumns.setLayoutX(180);
        enterColumns.setLayoutY(150);


        Button enter = new Button("Enter");
        enter.setLayoutX(180);
        enter.setLayoutY(190);
        enter.setDisable(true);     // disabled by default, to prevent invalid inputs

        Pane hbox = new Pane();
        hbox.getChildren().add(labelRows);
        hbox.getChildren().add(labelColumns);
        hbox.getChildren().add(enterRows);
        hbox.getChildren().add(enterColumns);
        hbox.getChildren().add(enter);

        // event handler for validating input in the rows text box
        enterRows.setOnKeyReleased(e -> {
            int t1 = -1;
            int t2 = -1;
            try{
            t1 = Integer.parseInt( enterRows.getText());
            t2 = Integer.parseInt( enterColumns.getText());
            }
            catch (Exception exception){
                enter.setDisable(true);
            }

            if (t1 > 0 && t1 <= 20 && t2 > 0 && t2 <= 20 ){
                enter.setDisable(false);
            }
            else {
                enter.setDisable(true);
            }
    });
        // event handler for validating input in the columns text box
        enterColumns.setOnKeyReleased(e -> {
                int t1 = -1;
                int t2 = -1;
                try{
                    t1 = Integer.parseInt( enterRows.getText());
                    t2 = Integer.parseInt( enterColumns.getText());
                }
                catch (Exception exception){
                    enter.setDisable(true);
                }

                if (t1 > 0 && t1 <= 20 && t2 > 0 && t2 <= 20 ){
                    enter.setDisable(false);
                }
                else {
                    enter.setDisable(true);
                }
        });

        enter.setOnAction(new EventHandler<ActionEvent>() {
            // after button pressed, executes minesweeper
            @Override
            public void handle(ActionEvent event) {
                int rows = Integer.parseInt(enterRows.getText());
                int columns = Integer.parseInt(enterColumns.getText());
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
                                if (button == MouseButton.PRIMARY) { // left click
                                    if (cell.isClickable) {
                                        if (cell.isMine){
                                            for (int row = 0; row < grid.height; row++){
                                                for (int column = 0; column < grid.width; column++){
                                                    Cells cell = grid.cells[row][column].get(0);
                                                    cell.isClickable = false;
                                                    cell.state = "opened";
                                                    if (cell.isMine) {
                                                        cell.appearance = "✳";
                                                        cell.setText(cell.appearance);
                                                    }
                                                }
                                            }
                                            grid.showMines();
                                            cell.appearance = "✳";
                                            // reveal all mines
                                        }
                                        else if (cell.adjacentMinesCount > 0){
                                            cell.appearance = "" + cell.adjacentMinesCount;
                                            grid.numberedCell((cell.x / 35), (cell.y / 35));
                                        }
                                        else if (cell.adjacentMinesCount == 0){
                                            cell.appearance = "" + cell.adjacentMinesCount;
                                            grid.adjacentChecker((cell.x / 35), (cell.y / 35));
                                            for (int row = 0; row < grid.height; row++) {
                                                for (int column = 0; column < grid.width; column++) {
                                                    Cells cell = grid.cells[row][column].get(0);
                                                    if (cell.isChecked) {
                                                        cell.appearance = "" + cell.adjacentMinesCount;
                                                        cell.setText(cell.appearance);
                                                    }
                                                }
                                            }
                                        }
                                        cell.setText(cell.appearance);
                                    }
                                }
                                else { // right click
                                    //if unopened, place flag
                                    if (Objects.equals(cell.state, "unopened")){
                                        cell.appearance = "\uD83D\uDEA9";
                                        cell.setText(cell.appearance);
                                        grid.placeFlag((cell.x / 35), (cell.y / 35));    // divide by 35 to make compatible with console coords
                                    }
                                    // if already flagged, removes flag
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

        });







        Scene scene1 = new Scene(hbox, 500, 500);
        stage.setScene(scene1);
        stage.show();
    }


    public static void main(String[] args) {
        launch();

    }

}




















































