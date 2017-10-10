package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	
	private int _result;
	
	private int _levelNum;
	
	@FXML
	public void initialize() {
		//_result = ClassicTestPage.getResult(); 0-1  2-4 5-8  9-10
		//_levelNum = CLassicTestPage.getLevel();
		_result = 0;
		_number.setText(_result + "/10");
		
		if (_result < 2) {
			setNoStar();
			_message.setText("Not Achieved..");
			_nextLevel.setVisible(false);
		}
		else if (_result < 5) {
			setOneStar();
			_message.setText("Achieved~");
		}
		else if (_result < 9) {
			setTwoStar();
			_message.setText("Merit!");
		}
		else {
			setThreeStar();
			_message.setText("Excellence!");
		}
		
	}
	
	@FXML
	public void handlePressMenu(MouseEvent event) {
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicMenuPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void handlePressNextLevel(MouseEvent event) {
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void handlePressReplay(MouseEvent event) {
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	private void setNoStar() {
		_full1.setVisible(false);
		_full2.setVisible(false);
		_full3.setVisible(false);
	}
	
	private void setOneStar() {
		_full2.setVisible(false);
		_full3.setVisible(false);
	}
	
	private void setTwoStar() {
		_full3.setVisible(false);
	}
	
	private void setThreeStar() {
		//
	}
}
