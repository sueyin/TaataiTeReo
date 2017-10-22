package application.controller;

import application.TataiApp;
import application.model.question.Question;
import application.viewModel.SceneSwitch;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXSpinner;
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
    public void initialize() {
    	_play.setVisible(false);
    	_loading.setVisible(false);
    	_process.setVisible(false);
    	_rightOrWrong.setVisible(false);
    	_youSaid.setVisible(false);
    	_answerIs.setVisible(false);
    }

    @FXML
    public void handlePressRecord(MouseEvent event) {
        _play.setVisible(false);
        _record.setVisible(false);
        _process.setVisible(true);
        _loading.setVisible(true);
        _q.test();
    }

    //Change GUI methods
    public void rightGUI(){
    	_process.setVisible(false);
    	_loading.setVisible(false);
    	_play.setVisible(true);
        _rightOrWrong.setText(SceneSwitch.getBundle().getString("keyCorrect"));
    	_rightOrWrong.setVisible(true);
        _youSaid.setText("You said " + _q.getAnswer());
    	_youSaid.setVisible(true);
    	_answerIs.setVisible(false);
        _record.setVisible(false);
        _next.setVisible(true);
    }

    public void tryAgainGUI(){
    	_process.setVisible(false);
    	_loading.setVisible(false);
    	_play.setVisible(true);
        _rightOrWrong.setText(SceneSwitch.getBundle().getString("keyNotReally"));
    	_rightOrWrong.setVisible(true);
        _youSaid.setText(SceneSwitch.getBundle().getString("keyYouSoundsLike")+" " + _q.getRead());
    	_youSaid.setVisible(true);
    	_answerIs.setVisible(false);
        _record.setText(SceneSwitch.getBundle().getString("keyTryAgain"));
        _record.setVisible(true);
    }

    public void wrongGUI(){
    	_process.setVisible(false);
    	_loading.setVisible(false);
    	_play.setVisible(true);
        _rightOrWrong.setText(SceneSwitch.getBundle().getString("keyUseUpChance"));
    	_rightOrWrong.setVisible(true);
        _youSaid.setText(SceneSwitch.getBundle().getString("keyYouSoundsLike")+" " + _q.getRead());
    	_youSaid.setVisible(true);
        _answerIs.setText(SceneSwitch.getBundle().getString("keyAnswerIs")+" " +  _q.getAnswer());
    	_answerIs.setVisible(true);
        _record.setVisible(false);
        _next.setVisible(true);
    }

    public void emptyRecordGUI(){
        _process.setVisible(false);
        _loading.setVisible(false);
        _play.setVisible(true);
        _rightOrWrong.setText(SceneSwitch.getBundle().getString("keyNothingRecorded"));
        _rightOrWrong.setVisible(true);
        _youSaid.setText(SceneSwitch.getBundle().getString("keyCheckMicrophone"));
        _youSaid.setVisible(true);
        _answerIs.setVisible(false);
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