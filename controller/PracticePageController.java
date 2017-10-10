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
	
	private boolean isOnlyNumber(String value) {
		if (value == null || value.equals("")){
			return false;
		}
	    return value.matches("^[0-9]+$");
	}
	
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
