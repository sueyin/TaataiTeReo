package application.controller;

import application.viewmodel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClassicFeedbackPageController {

	@FXML
	private Label _message;
	
	@FXML
	private Label _number;
	
	@FXML
	private Label _level;
	
	@FXML
	private ImageView _full1;
	
	@FXML
	private ImageView _full2;
	
	@FXML
	private ImageView _full3;
	
	@FXML
	private Button _nextLevel;
	
	@FXML
	private Label _exp;
	
	private int _result;
	
	private String _levelNum;
	
	@FXML
	public void initialize() {
		//Get date from the TestPage
		_levelNum = ClassicTestPageController.getLevel();
		_result = Integer.parseInt(ClassicTestPageController.getScore());

		//Update the result
		MainPageController.getUser().updateClassicRecord(_levelNum, ClassicTestPageController.getScore());

		//Change GUI correspondingly
		_level.setText(SceneSwitch.getBundle().getString("keyLevel") + " " + _levelNum);
		_number.setText(_result + "/10");
		if (_levelNum.equals("15")){
			_nextLevel.setVisible(false);
		}else{
			_nextLevel.setVisible(true);
		}

		//Display Feedback according to the score
		//display no star if between 0-2
		if (_result < 2) {
			setNoStar();
			_message.setText(SceneSwitch.getBundle().getString("keyNotAchieved"));
			_nextLevel.setVisible(false);
			_exp.setVisible(false);
		}
		//display 1 star is between 2-4
		else if (_result < 5) {
			setOneStar();
			_message.setText(SceneSwitch.getBundle().getString("keyAchieved"));
			MainPageController.getUser().increaseExp(2);
			_exp.setText("+EXP 2");
		}
		//display 2 stars if between 5 -8
		else if (_result < 9) {
			setTwoStar();
			_message.setText(SceneSwitch.getBundle().getString("keyMerit"));
			MainPageController.getUser().increaseExp(5);
			_exp.setText("+EXP 5");
		}
		//display 3 stars if between 9-10
		else {
			setThreeStar();
			_message.setText(SceneSwitch.getBundle().getString("keyExcellence"));
			MainPageController.getUser().increaseExp(10);
			_exp.setText("+EXP 10");
		}
	}
	
	/**
	 * Switch to classic mode menu page when user press menu button
	 */
	@FXML
	public void handlePressMenu(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/ClassicMenuPage.fxml");
	}
	
	/**
	 * Switch to next level
	 */
	@FXML
	public void handlePressNextLevel(MouseEvent event) {
		int nextLevel = Integer.parseInt(_levelNum) + 1;
		ClassicMenuPageController.setSelectedLevel(Integer.toString(nextLevel));
		
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/ClassicTestPage.fxml");
	}
	/**
	 * replay this level again
	 */
	@FXML
	public void handlePressReplay(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/ClassicTestPage.fxml");
		
	}

	/*
		Private method about GUI
	 */

	/**
	 * Set the reward to zero star
	 */
	private void setNoStar() {
		_full1.setVisible(false);
		_full2.setVisible(false);
		_full3.setVisible(false);
	}

	/**
	 * Set the reward to one star
	 */
	private void setOneStar() {
		_full1.setVisible(true);
		_full2.setVisible(false);
		_full3.setVisible(false);
	}

	/**
	 * Set the reward to two stars
	 */
	private void setTwoStar() {
		_full1.setVisible(true);
		_full1.setVisible(true);
		_full3.setVisible(false);
	}

	/**
	 * Set the reward to three stars
	 */
	private void setThreeStar() {
		_full1.setVisible(true);
		_full2.setVisible(true);
		_full3.setVisible(true);
	}
}
