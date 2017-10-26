package application.controller;

import javafx.fxml.FXML;

import com.jfoenix.controls.JFXButton;


import application.viewModel.SceneSwitch;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class PracticePageController {
	@FXML
	private Label _title;
	@FXML
	private Label _hardInstruction;
	@FXML
	private Label _easyInstruction;
	@FXML
	private JFXButton _mainReturn;
	@FXML
	private Label _customInstruction;
	@FXML
	private JFXButton _easy;
	@FXML
	private JFXButton _hard;
	@FXML
	private TextField _textField;
	@FXML
	private JFXButton _custom;
	@FXML
	private Label _instruction;
	@FXML
	private JFXButton _disabledButton;

	private static String _question;

	@FXML
	public void initialize() {
		_disabledButton.setVisible(false);
	}

	/**
	 * when user press return, switch back to main page
	 */
	@FXML
	public void handlePressMainReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
	/**
	 * when user press easy, then switch to test page and generate an easy question 
	 * @param event
	 */
	@FXML
	public void handlePressEasy(MouseEvent event) {
		_question = Integer.toString((int) ((Math.random()*10)+1));
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeTestPage.fxml");
	}
	/**
	 * when user press hard, then switch to test page and generate an hard question 
	 * @param event
	 */
	@FXML
	public void handlePressHard(MouseEvent event) {
		_question = Integer.toString((int) ((Math.random()*100)+1));
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeTestPage.fxml");
	}
	/**
	 * when user enters or changes in the text field, read in new input, and checks if it's valid, change the custom button's graphic according to the result
	 * @param event
	 */
	@FXML
	public void handleTextChange(KeyEvent event) {
		//read input from textField
		String input = _textField.getText();
		//remove all spaces in the input
		input = input.replaceAll("\\s+","");
		//when input is empty, display "#"
		if (input.equals("")){
			_custom.setText("#");
		}
		//if the input is number between 1 -99, then display the number on the button 
		else if (isOnlyNumber(input)){
			int num = Integer.parseInt(input);
			if (num > 0 && num < 100) {
				_custom.setText(input);
				_disabledButton.setVisible(false);
			}
			//input is out of range, show "input undefined"
			else {
				_disabledButton.setVisible(true);
			}
		}
		//input is not a number, show "input undefined"
		else {
			_disabledButton.setVisible(true);
		}
	}


	/**
	 * When user press custom, read input from textfield, and switch to test page
	 * @param event
	 */
	@FXML
	public void handlePressCustom(MouseEvent event) {
		_question = _textField.getText();
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeTestPage.fxml");
		
	}

	/**
	 * switch to statistic page
	 * @param event
	 */
	@FXML
	public void handlePressStatistic(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeStatisticPage.fxml");
	}

	/**
	 * checks if a string consists of only numbers 
	 */
	private boolean isOnlyNumber(String value) {
		if (value == null || value.equals("")){
			return false;
		}
		return value.matches("^[0-9]+$");
	}

	public static String getQuestion() {
		return _question;
	}

}
