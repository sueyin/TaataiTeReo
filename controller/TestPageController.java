package application.controller;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
    
    @FXML
    private Button _play;

    protected static int _score;

    public void addScore(){
        _score = _score + 1;
    }

    //Change GUI methods

    public void rightGUI(){
        _message.setText("You got it Right ! XD");
        _record.setVisible(false);
        _next.setVisible(true);
    }

    public void tryAgainGUI(){
        _message.setText("Not really.. One more chance");
        _record.setText("Try Again");
        _record.setVisible(true);
    }

    public void wrongGUI(){
        _message.setText("You have used up your chance :(");
        _record.setVisible(false);
        _next.setVisible(true);
    }
    
	@FXML
	public void handlePressPlay(MouseEvent event) {
		//TODO function for play recording 
		System.out.println("play");
	}

}