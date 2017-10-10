package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PracticePageController {
	
	@FXML
	private TextField _textField;
	
	@FXML
	private Button _custom;
	
	@FXML
	private Button _disabledButton;
	
	
	@FXML
	public void initialize() {
		_disabledButton.setVisible(false);
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
	public void handlePressReturn(MouseEvent event) {
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
	 * This method switches to custom practice page when custom  button is pressed 
	 */
	@FXML
	public void handlePressCustom(MouseEvent event) {
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
}
