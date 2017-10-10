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


    private static String _score;


    //Change GUI methods

    public void rightGUI(){
        _message.setText("You got it Right!");
        _record.setVisible(false);
        _next.setVisible(true);
    }

    public void tryAgainGUI(){
        _message.setText("Not really.. One more chance");
        _record.setText("Try Again");
        _record.setVisible(true);
    }

    public void wrongGUI(){
        _message.setText("dd.You are hopeless");
        _record.setVisible(false);
        _next.setVisible(true);
    }

}