package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class TestPageController {
    @FXML
    protected Label _level;

    @FXML
    protected Label _message;

    @FXML
    protected Label _question;

    @FXML
    protected Button _record;

    @FXML
    protected Button _next;



    //Change GUI methods

    public void rightGUI(){
        System.out.println("right");
    }

    public void tryAgainGUI(){
        System.out.println("Try Again");
    }

    public void WrongGUI(){
        System.out.println("Wrong");
    }

}