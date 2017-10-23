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

	// Event Listener on JFXButton[#_mainReturn].onMouseClicked
	@FXML
	public void handlePressMainReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
	// Event Listener on JFXButton[#_easy].onMouseClicked
	@FXML
	public void handlePressEasy(MouseEvent event) {
		_question = Integer.toString((int) ((Math.random()*10)+1));
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeTestPage.fxml");
	}
	// Event Listener on JFXButton[#_hard].onMouseClicked
	@FXML
	public void handlePressHard(MouseEvent event) {
		_question = Integer.toString((int) ((Math.random()*100)+1));
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeTestPage.fxml");
	}
	// Event Listener on TextField[#_textField].onKeyReleased
	@FXML
	public void handleTextChange(KeyEvent event) {
		String input = _textField.getText();
		input = input.replaceAll("\\s+","");
		if (input.equals("")){
			_custom.setText("#");
		}
		else if (isOnlyNumber(input)){
			int num = Integer.parseInt(input);
			if (num > 0 && num < 100) {
				_custom.setText(input);
				_disabledButton.setVisible(false);
			}
			else {
				_disabledButton.setVisible(true);
			}
		}
		else {
			_disabledButton.setVisible(true);
		}
	}


	// Event Listener on JFXButton[#_custom].onMouseClicked
	@FXML
	public void handlePressCustom(MouseEvent event) {
		_question = _textField.getText();
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeTestPage.fxml");
		
	}


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
