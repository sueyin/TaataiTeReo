package application.controller;

import java.io.IOException;

import application.model.Question.Question;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PracticePageController extends TestPageController {
	
	@FXML
	private TextField _textField;
	
	@FXML
	private Button _custom;
	
	@FXML
	private Button _disabledButton;

	@FXML
	private Button _easy;

	@FXML
	private Button _hard;

	@FXML
	private Button _mainReturn;

	@FXML
	private Button _testReturn;

	@FXML
	private Button _repeat;

	@FXML
	private Label _question;

	@FXML
	private Label _easyInstruction;

	@FXML
	private Label _hardInstruction;

	@FXML
	private Label _customInstruction;


	private Question _q;

	private String _input;
	
	
	@FXML
	public void initialize() {
		_disabledButton.setVisible(false);
		beferoTestGUI();
	}
	
	/**
	 * This method updates the text of the custom button when user enters number in text field 
	 */
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
	
	/**
	 * checks if a string consists of only numbers 
	 */
	private boolean isOnlyNumber(String value) {
		if (value == null || value.equals("")){
			return false;
		}
	    return value.matches("^[0-9]+$");
	}
	
	/**
	 * This method switches page to main page when return is pressed 
	 */
	@FXML
	public void handlePressMainReturn(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/MainPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@FXML
	public void handlePressTestReturn(MouseEvent event) {
		beferoTestGUI();
		//TODO _q.cancel();
	}

	@FXML
	public void handlePressRecord(MouseEvent event){

	}

	/**
	 * This method switches to easy practice page wehn easy button is pressed 
	 */
	@FXML
	public void handlePressEasy(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/MainPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * This method switches to hard practice page when hard button is pressed 
	 */
	@FXML
	public void handlePressHard(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/MainPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * This method collect the valid user input
	 */
	@FXML
	public void handlePressCustom(MouseEvent event) {
        _input = _textField.getText();

	}


	/**
	 * Adjust GUI to the test state
	 */
	private void testGUI(){
		_textField.setVisible(false);
		_custom.setVisible(false);
		_easy.setVisible(false);
		_hard.setVisible(false);
		_repeat.setVisible(false);
		_question.setVisible(true);
		_easyInstruction.setVisible(false);
		_hardInstruction.setVisible(false);
		_customInstruction.setVisible(false);
		_mainReturn.setVisible(false);
		_testReturn.setVisible(true);
		//TODO set statistic invisible
	}

	/**
	 * Adjust GUI to after test state
	 */
	private void afterTestGUI(){
		_textField.setVisible(true);
		_custom.setVisible(true);
		_easy.setVisible(true);
		_hard.setVisible(true);
		_repeat.setVisible(true);
		_question.setVisible(true);
		_easyInstruction.setVisible(false);
		_hardInstruction.setVisible(false);
		_customInstruction.setVisible(false);
		_mainReturn.setVisible(true);
		_testReturn.setVisible(false);
		//TODO set statistic visible
	}

	/**
	 * Adjust GUI to the before test state
	 */
	private void beferoTestGUI(){
		_textField.setVisible(true);
		_custom.setVisible(true);
		_easy.setVisible(true);
		_hard.setVisible(true);
		_repeat.setVisible(false);
		_question.setVisible(true);
		_easyInstruction.setVisible(true);
		_hardInstruction.setVisible(true);
		_customInstruction.setVisible(true);
		_mainReturn.setVisible(true);
		_testReturn.setVisible(false);
		//TODO set statistic invisible
	}

}
