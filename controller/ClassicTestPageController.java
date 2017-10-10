package application.controller;


import application.model.Question.ClassicQuestion;
import application.model.Question.ClassicQuestionSuite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ClassicTestPageController extends TestPageController {

	private static String _selectedLevel;

	private ClassicQuestionSuite _qs;

	private ClassicQuestion _q;
	
	@FXML
	public void initialize() {
		//Decide the which level the user chose
		_selectedLevel = ClassicMenuPageController.getSelectedLevel();
		_level.setText("Level "+_selectedLevel);
		//Generate the correspongding quesition bank
		_qs = new ClassicQuestionSuite(_selectedLevel);
		//Set the first question
		_q = new ClassicQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
		_question.setText(_qs.getQuestion());
		//Initialize GUI
		_record.setVisible(true);
		_record.setText("Record");
		_next.setVisible(false);
		_next.setText("Next");
	}
	
	/**
	 *This method handles event when the button is pressed (button => "record", "next question")
	 */
	@FXML
	public void handlePressButton(MouseEvent event) {
		_q.test();
		_qs.collectResult(_q.getResult());
		if (_qs.getIndexNumber() == 10){
			_next.setText("Completed");
		}
	}

	@FXML
	public void handlePressNext(MouseEvent event) {
		if (_next.getText() == "Completed"){
			try {
				//Count the score (how many right attempts)
				int score = 0;
				for (boolean b : _qs.getResults()){
						if (b){
							score++;
						}
				}
				MainPageController.getUser().updateClassicRecord(_selectedLevel, Integer.toString(score));
				Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicFeedbackPage.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
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


	
}
