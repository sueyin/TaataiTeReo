package application.controller;

import application.TataiApp;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.*;
import java.io.File;

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
        System.out.println("Score is " +_score);
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
        try {
            File audio = new File(TataiApp.getTempDir() + "temp.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(audio);
            AudioFormat format = stream.getFormat();
            DataLine.Info  info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        }catch (Exception e){
            System.out.println("erorr" + e);
        }
	}

}