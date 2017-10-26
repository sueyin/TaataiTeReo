package application.controller;

import application.TataiApp;
import application.model.question.Question;
import application.viewmodel.SceneSwitch;

import com.jfoenix.controls.JFXSpinner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.*;
import java.io.File;

/**
 * All test pages inherit this abstract class
 * @author shenhong
 *
 */
public abstract class TestPageController {
    //GUI
    @FXML
    protected Label _mode;

    @FXML
    protected Label _topRight;

    @FXML
    protected Label _question;

    @FXML
    protected Label _youSaid;

    @FXML
    protected Label _rightOrWrong;

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

    /**
     * method that initialize GUI to before recording
     */
    @FXML
    public void initialize() {
    	_play.setVisible(false);
    	_loading.setVisible(false);
    	_process.setVisible(false);
    	_rightOrWrong.setVisible(false);
    	_youSaid.setVisible(false);
    	_answerIs.setVisible(false);
    }

    //==================================================================================================================
    /**
     * this method is called when user press record, shows the recording page
     * @param event
     */
    @FXML
    public void handlePressRecord(MouseEvent event) {
        _play.setVisible(false);
        _record.setVisible(false);
        _process.setVisible(true);
        _loading.setVisible(true);
        _q.test();
    }

    /**
     * this method is called when user press the play button to hear recording
     * @param event
     */
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
            ;
        }
    }

    //==================================================================================================================
    /*
        Update GUI methods
     */

    /**
     * this method is called when the question is answered correctly, update to GUI to display correct
     */
    public void rightGUI(){
    	_process.setVisible(false);
    	_loading.setVisible(false);
    	_play.setVisible(true);
        _rightOrWrong.setText(SceneSwitch.getBundle().getString("keyCorrect"));
    	_rightOrWrong.setVisible(true);
        //_youSaid.setText(SceneSwitch.getBundle().getString("keyYouSaid")+" " + _q.getRead());
        _youSaid.setText(SceneSwitch.getBundle().getString("keyYouSaid")+" " + _q.getAnswer());
    	_youSaid.setVisible(true);
    	_answerIs.setVisible(false);
        _record.setVisible(false);
        _next.setVisible(true);
    }

    /**
     * this method is called when the user gets the question wrong the first time, update to GUI to display try again
     */
    public void tryAgainGUI(){
    	_process.setVisible(false);
    	_loading.setVisible(false);
    	_play.setVisible(true);
        if (_q.getRead() == null){
            nothingRecordedMsg();
        }else {
            _rightOrWrong.setText(SceneSwitch.getBundle().getString("keyNotReally"));
            _rightOrWrong.setVisible(true);
            _youSaid.setText(SceneSwitch.getBundle().getString("keyYouSoundsLike") + " " + _q.getRead());
            _youSaid.setVisible(true);
            _answerIs.setVisible(false);
        }
        _record.setText(SceneSwitch.getBundle().getString("keyTryAgain"));
        _record.setVisible(true);
    }

    /**
     * this method is called when the question is answered incorrectly two times, update to GUI to display incorrect,
     * and runs out of chances
     */
    public void wrongGUI(){
    	_process.setVisible(false);
    	_loading.setVisible(false);
    	_play.setVisible(true);
        if (_q.getRead() == null){
            nothingRecordedMsg();
            _answerIs.setText(SceneSwitch.getBundle().getString("keyAnswerIs")+" " +  _q.getAnswer());
        }else{
            _rightOrWrong.setText(SceneSwitch.getBundle().getString("keyUseUpChance"));
            _rightOrWrong.setVisible(true);
            _youSaid.setText(SceneSwitch.getBundle().getString("keyYouSoundsLike")+" " + _q.getRead());
            _youSaid.setVisible(true);
            _answerIs.setText(SceneSwitch.getBundle().getString("keyAnswerIs")+" " +  _q.getAnswer());
        }
    	_answerIs.setVisible(true);
        _record.setVisible(false);
        _next.setVisible(true);
    }

    //Inform the user to check the microphone as nothing was recorded.
    private void nothingRecordedMsg() {
        _rightOrWrong.setText(SceneSwitch.getBundle().getString("keyNothingRecorded"));
        _rightOrWrong.setVisible(true);
        _youSaid.setText(SceneSwitch.getBundle().getString("keyCheckMicrophone"));
        _youSaid.setVisible(true);
        _answerIs.setVisible(false);
    }


    //==================================================================================================================

	/**
	 * cancels recording and comparing
	 */
	protected void cancelQuestion(){
        _q.cancel();
    }

}