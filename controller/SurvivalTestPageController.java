package application.controller;

import com.jfoenix.controls.JFXButton;

import application.confirmation.ConfirmationModel;
import application.model.question.OneChanceQuestion;
import application.model.survival.SurvivalQuestionSuite;
import application.viewmodel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SurvivalTestPageController extends TestPageController{


	@FXML
	private ImageView _heart;

	@FXML
	private ImageView _heart1;

	@FXML
	private ImageView _heart2;
	
	@FXML
	private JFXButton _backToMain;
	
	@FXML
	private Label _gameOver;
	
	@FXML
	private Label _title;
	
	@FXML
	private Label _exp;

	//Functionality
	private SurvivalQuestionSuite _qs;

	private int _live;



	/**
	 * This method initialize the survival test page, set three lives
	 */
	@FXML
	public void initialize(){
		super.initialize();
		_next.setVisible(false);
		_gameOver.setVisible(false);
		_backToMain.setVisible(false);
		_exp.setVisible(false);
		//set score to 0
		_topRight.setText(SceneSwitch.getBundle().getString("keyScore0"));
		
		//set 3 lives
		_live = 3;
		fullHeart();

		//create a new question suite
		_qs = new SurvivalQuestionSuite();
		_q = new OneChanceQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
		_question.setText(_q.getQuestion());
	}

	/**
	 * when the user press next, switch to the next question
	 * @param event
	 */
	@FXML
	public void handlePressNext(MouseEvent event) {
		//create a new question
		_qs.nextQuestion();
		_q = new OneChanceQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
		_question.setText(_q.getQuestion());
		
		//update GUI
		_record.setVisible(true);
		_next.setVisible(false);
		_youSaid.setVisible(false);
		_answerIs.setVisible(false);
		_rightOrWrong.setVisible(false);
	}

	/**
	 * return to main page when user press return an cancel the recording of the question 
	 * @param event
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		//opens a window that confirms if the user wants to quit 
		ConfirmationModel confirmation = new ConfirmationModel((Stage) ((Node) event.getSource()).getScene().getWindow(),SceneSwitch.getBundle().getString("keySureReturnClassic"), SceneSwitch.getBundle().getString("keyReturn"), SceneSwitch.getBundle().getString("keyStay"));
		boolean confirm = confirmation.createPopUp();
		//if user confirm existing this page, then switch page
		if (confirm){
			//cancel the recording and compare task 
			super.cancelQuestion();
			SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
			load.switchScene("/application/view/MainPage.fxml");
		}

	}


	@FXML
	public void handlePressReturnToMain(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");

	}
	/**
	 * call method wrongGUI() in TestPage Controller, and minus one life. If no lift, then switch to game over
	 */
	@Override
	public void wrongGUI(){
		super.wrongGUI();
		_live--;
		if (_live == 2){
			twoHeart();
		}else if (_live == 1){
			oneHeart();
		//lose the game, end
		}else{
			noHeart();
			_gameOver.setVisible(true);
			_backToMain.setVisible(true);
			_title.setVisible(false);
			_play.setVisible(false);
			//calculate EXP point earned and display it to the user
			MainPageController.getUser().updateSurvivalScore(_score);
			_exp.setText("+EXP "+_score);
			_exp.setVisible(true);
			calculateExp();
		}
	}

	/**
	 * call method rightGUI() in TestPage Controller, update the score label
	 */
	@Override
	public void rightGUI(){
		super.rightGUI();
		_topRight.setText(SceneSwitch.getBundle().getString("keyScore") +" " + _score);
	}


	/**
	 * calculates exp value 
	 */
	private void calculateExp(){
		int exp;
		if (_score < 10){
			exp = _score;
		}else if (_score < 25){
			exp = 10 + (_score - 10) * 2;
		}else{
			exp = 10 + 30 + (_score - 25) * 3;
		}
		MainPageController.getUser().increaseExp(exp);
	}

	/*
		Support methods
	 */

	private void noHeart(){
		_heart.setVisible(false);
		_heart1.setVisible(false);
		_heart2.setVisible(false);
		_next.setVisible(false);
		_record.setVisible(false);
	}

	private void oneHeart(){
		_heart.setVisible(false);
		_heart1.setVisible(false);
		_heart2.setVisible(true);
	}

	private void twoHeart(){
		_heart.setVisible(false);
		_heart1.setVisible(true);
		_heart2.setVisible(true);
	}

	private void fullHeart(){
		_heart.setVisible(true);
		_heart1.setVisible(true);
		_heart2.setVisible(true);
	}


}
