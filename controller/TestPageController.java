package application.controller;

import application.TataiApp;
import application.model.question.Question;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXSpinner;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QDecoderStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.*;
import java.io.File;

public abstract class TestPageController {
    //GUI
    @FXML
    protected Label _mode;

    @FXML
    protected Label _topRight;

    @FXML
    protected Label _question;

    @FXML
    protected Label _rightOrWrong;

    @FXML
    protected Label _youSaid;

    @FXML
    protected Label _answerIs;

    @FXML
    protected Button _record;

    @FXML
    protected JFXSpinner _loading;

    @FXML
    protected Label _process;

    @FXML
    protected Button _play;

    @FXML
    protected Button _next;

    @FXML
    protected Button _return;


    //Functionality
    protected Question _q;

    protected static int _score;


    //Method
    public void addScore(){
        _score = _score + 1;
    }


    @FXML
    public void handlePressRecord(MouseEvent event) {
        _play.setVisible(false);
        _record.setVisible(false);
        _process.setVisible(true);
        _loading.setVisible(true);
        _q.test();
        collectResult();
    }

    public abstract void collectResult();


    //Change GUI methods
    public void rightGUI(){
        _rightOrWrong.setText("Correct");
        _rightOrWrong.setVisible(true);
        _youSaid.setText("You said " + _q.getAnswer());
        _youSaid.setVisible(true);
        _record.setVisible(false);
        _next.setVisible(true);
    }

    public void tryAgainGUI(){
        _rightOrWrong.setText("Not really. You can try again");
        _rightOrWrong.setVisible(true);
        _youSaid.setText("You sounds like " + _q.getRead());
        _youSaid.setVisible(true);
        _record.setText("Try Again");
        _record.setVisible(true);
    }

    public void wrongGUI(){
        _rightOrWrong.setText("Not really. You have used up the chance");
        _rightOrWrong.setVisible(true);
        _youSaid.setText("You sounds like " + _q.getRead());
        _youSaid.setVisible(true);
        _answerIs.setText("The answer is " +  _q.getAnswer());
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