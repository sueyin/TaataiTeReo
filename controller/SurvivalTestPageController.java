package application.controller;

import application.model.question.OneChanceQuestion;
import application.model.question.SurvivalQuestionSuite;
import application.viewModel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

	//Functionality
	private SurvivalQuestionSuite _qs;

	private int _live;



	@FXML
	public void initialize(){
		super.initialize();
		_next.setVisible(false);
		_topRight.setText(SceneSwitch.getBundle().getString("keyScore0"));
		_live = 3;
		fullHeart();

		_qs = new SurvivalQuestionSuite();
		_q = new OneChanceQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
		_question.setText(_q.getQuestion());
	}

	/*
		Handler
	 */
	@FXML
	public void handlePressNext(MouseEvent event) {
		_qs.nextQuestion();
		_q = new OneChanceQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
		_question.setText(_q.getQuestion());
		_record.setVisible(true);
		_next.setVisible(false);
		_youSaid.setVisible(false);
		_answerIs.setVisible(false);
		_rightOrWrong.setVisible(false);
	}

	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}


	@Override
	public void wrongGUI(){
		super.wrongGUI();
		_live--;
		if (_live == 2){
			twoHeart();
		}else if (_live == 1){
			oneHeart();
		}else{
			noHeart();
			_youSaid.setText(SceneSwitch.getBundle().getString("keyGameOver"));
			_rightOrWrong.setVisible(false);
			_answerIs.setVisible(false);
			_play.setVisible(false);
			MainPageController.getUser().updateSurvivalScore(_score);
		}
	}

	@Override
	public void rightGUI(){
		super.rightGUI();
		System.out.println(_score);
		_topRight.setText(SceneSwitch.getBundle().getString("keyScore") +" " + _score);
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
