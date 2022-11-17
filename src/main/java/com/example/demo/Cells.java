package com.example.demo;
import javafx.scene.control.Button;

public class Cells extends Button{
    String state = "unopened";         // default state for new cell instances >> "unopened";
    String appearance = " ";           // how cell will look on screen, default will be ".";
    boolean isFlag = false;
    boolean isClickable = true;
    boolean isChecked = false;
    int adjacentMinesCount = 0;
    boolean isMine = false;

    int x;

    int y;





}
