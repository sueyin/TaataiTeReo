package application.controller;


import application.model.Question.ClassicQuestion;
import application.model.Question.ClassicQuestionSuite;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomTestPageController extends TestPageController {

	private static String _selectedLevel;

	private ClassicQuestionSuite _qs;

	private ClassicQuestion _q;

	@FXML
	private JFXRadioButton _q1;
	@FXML
	private JFXRadioButton _q2;
	@FXML
	private JFXRadioButton _q3;
	@FXML
	private JFXRadioButton _q4;
	@FXML
	private JFXRadioButton _q5;
	@FXML
	private JFXRadioButton _q6;
	@FXML
	private JFXRadioButton _q7;
	@FXML
	private JFXRadioButton _q8;
	@FXML
	private JFXRadioButton _q9;
	@FXML
	private JFXRadioButton _q10;


	@FXML
	public void initialize() {
		//Decide the which level the user chose
		_selectedLevel = ClassicMenuPageController.getSelectedLevel();

		//Generate the correspongding quesition bank
		_qs = new ClassicQuestionSuite(_selectedLevel);

		//Set the first question
		_q = new ClassicQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
		_question.setText(_qs.getQuestion());
		_score = 0;

		//Initialize GUI
		_level.setText("Level "+_selectedLevel);
		_record.setVisible(true);
		_record.setText("Record");
		_next.setVisible(false);
		_next.setText("Next");

		_message.setVisible(false);
		_q1.setId("onQuestion");
	}

	/**
	 *This method handles event when the button is pressed (button => "record", "next question")
	 */
	@FXML
	public void handlePressButton(MouseEvent event) {
		_message.setVisible(true);
		_q.test();
		_qs.collectResult(_q.getResult());
		if (_qs.getIndexNumber() == 10){
			_next.setText("Completed");
		}
	}

	@FXML
	public void handlePressNext(MouseEvent event) {
		
		if (_next.getText() == "Completed"){
			System.out.println(_score);
			MainPageController.getUser().updateClassicRecord(_selectedLevel, Integer.toString(_score));
			try {
				//Count the score (how many right attempts)
				int score = 0;
				for (boolean b : _qs.getResults()){
					if (b){
						score++;
					}
				}
				//MainPageController.getUser().updateClassicRecord(_selectedLevel, Integer.toString(score));
				Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicFeedbackPage.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			//Update GUI
			boolean result = _q.getResult();
			int index = _qs.getIndexNumber();
			if (result) {
				if (index ==1) {
					_q1.setSelected(true);
					_q1.setId("radio_correct");

					_q2.setId("onQuestion");
				}
				else if (index == 2) {
					_q2.setSelected(true);
					_q2.setId("radio_correct");

					_q3.setId("onQuestion");
				}
				else if (index == 3) {
					_q3.setSelected(true);
					_q3.setId("radio_correct");
					
					_q4.setId("onQuestion");
				}
				else if (index == 4) {
					_q4.setSelected(true);
					_q4.setId("radio_correct");
					
					_q5.setId("onQuestion");
				}
				else if (index == 5) {
					_q5.setSelected(true);
					_q5.setId("radio_correct");
					
					_q6.setId("onQuestion");
				}
				else if (index == 6) {
					_q6.setSelected(true);
					_q6.setId("radio_correct");
					
					_q7.setId("onQuestion");
				}
				else if (index == 7) {
					_q7.setSelected(true);
					_q7.setId("radio_correct");
					
					_q8.setId("onQuestion");
				}
				else if (index == 8) {
					_q8.setSelected(true);
					_q8.setId("radio_correct");
					
					_q9.setId("onQuestion");
				}
				else if (index == 9) {
					_q9.setSelected(true);
					_q9.setId("radio_correct");
					
					_q10.setId("onQuestion");
				}
				else if (index == 10) {
					_q10.setSelected(true);
					_q10.setId("radio_correct");
				}
			}
			else {
				if (index ==1) {
			
					_q1.setId("radio_incorrect");
					
					_q2.setId("onQuestion");
				}
				else if (index == 2) {
				
					_q2.setId("radio_incorrect");
					
					_q3.setId("onQuestion");
				}
				else if (index == 3) {
					
					_q3.setId("radio_incorrect");
					
					_q4.setId("onQuestion");
				}
				else if (index == 4) {
				
					_q4.setId("radio_incorrect");

					_q5.setId("onQuestion");
				}
				else if (index == 5) {
					
					_q5.setId("radio_incorrect");

					_q6.setId("onQuestion");
				}
				else if (index == 6) {
		
					_q6.setId("radio_incorrect");

					_q7.setId("onQuestion");
				}
				else if (index == 7) {
				
					_q7.setId("radio_incorrect");

					_q8.setId("onQuestion");
				}
				else if (index == 8) {
				
					_q8.setId("radio_incorrect");

					_q9.setId("onQuestion");
				}
				else if (index == 9) {
				
					_q9.setId("radio_incorrect");

					_q10.setId("onQuestion");
				}
				else if (index == 10) {
				
					_q10.setId("radio_incorrect");
				}
			}
			//Change to the next question
			_qs.nextQuestion();
			_question.setText(_qs.getQuestion());
			_q = new ClassicQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
			//Update GUI
			_next.setVisible(false);
			_record.setVisible(true);
			_record.setText("Record");
			_message.setText("");
		}
	}

	@FXML
	public void handlePressReturn(MouseEvent event) {
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

	public static String getLevel(){
		return _selectedLevel;
	}

	public static String getScore(){
		return Integer.toString(_score);
	}

}
