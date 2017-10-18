package application.controller;

import javafx.fxml.FXML;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

import com.jfoenix.controls.JFXSpinner;

import application.model.question.PracticeQuestion;

public class TutorialTestPageController extends TestPageController {
	@FXML
	private Label _question;
	@FXML
	private JFXButton _return;

	@FXML
	private Label _message;
	@FXML
	private Label _recordInstr;
	@FXML
	private JFXButton _record;
	@FXML
	private JFXButton _play;
	@FXML
	private Label _questionInstr;
	@FXML
	private Label _title;
	@FXML
	private JFXSpinner _loading;
	@FXML
	private Label _returnInstr;
	@FXML
	private Label _loadInstr;
	@FXML
	private Label _playInstr;
	@FXML
	private Label _messageInstr;
	@FXML
	private Label _mainInstr;


	@FXML
	public void initialize() {
		_play.setVisible(false);
		_playInstr.setVisible(false);
		_loading.setVisible(false);
		_loadInstr.setVisible(false);
		_message.setVisible(false);
		_messageInstr.setVisible(false);

		_record.setStyle("-fx-effect: dropshadow(one-pass-box, lightgrey, 50, 0.6, 0.1, 0.1);-fx-background-color:#929292;-fx-background-radius:30");
		_recordInstr.setStyle("-fx-effect: dropshadow(one-pass-box, lightgrey, 50, 0.6, 0.1, 0.1);-fx-background-color:#696969;-fx-background-radius:30");

	}

	@FXML
	public void handlePressRecord(MouseEvent event) {
		_recordInstr.setVisible(false);
		_returnInstr.setVisible(false);
		_loading.setVisible(true);
		_loadInstr.setVisible(true);
		_mainInstr.setVisible(false);
		PracticeQuestion q = new PracticeQuestion("1",this);
		q.test();
	}

	public void rightGUI(){
		_message.setText("You got it Right ! XD");
		_record.setVisible(false);
		//TODO back to main
	}

	public void tryAgainGUI(){
		_message.setText("Not really.. One more chance");
		_record.setText("Try Again");
	}

	public void wrongGUI(){
		_message.setText("You have used up your chance :(");
		_record.setVisible(false);
		//TODO back to main
	}






}
