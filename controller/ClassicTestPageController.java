package application.controller;


import application.model.question.ClassicQuestion;
import application.model.question.ClassicQuestionSuite;
import application.viewModel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXRadioButton;

public class ClassicTestPageController extends TestPageController {

	@FXML
	private JFXRadioButton _q1, _q2, _q3, _q4, _q5, _q6, _q7, _q8, _q9, _q10;

	private static String _selectedLevel;

	private ClassicQuestionSuite _qs;

	@FXML
	public void initialize() {
		//Decide the which level the user chose
		_selectedLevel = ClassicMenuPageController.getSelectedLevel();

		//Generate the corresponding question bank
		_qs = new ClassicQuestionSuite(_selectedLevel);

		//Set the first question
		_q = new ClassicQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
		_question.setText(_qs.getQuestion());
		_score = 0;

		_question.setStyle("-fx-text-size: 100");
		//Initialize GUI
		_rightOrWrong.setVisible(false);
		_youSaid.setVisible(false);
		_answerIs.setVisible(false);

		if (Integer.parseInt(_selectedLevel) > 12){
			//Change font size for Strings
			_question.setStyle("-fx-font-size: 24");
		}else{
			//Change font size for numbers
			_question.setStyle("-fx-font-size: 130");
		}
		_q1.setId("onQuestion");
		_topRight.setText("Level "+_selectedLevel);
		_record.setVisible(true);
		_record.setText("Record");
		_next.setVisible(false);
		_next.setText("Next");
	}
	

	/**
	 *This method handles event when the button is pressed (button => "record", "next question")
	 */
	@FXML
	public void handlePressRecord(MouseEvent event) {
		super.handlePressRecord(event);
		if (_qs.getIndexNumber() == 10){
			_next.setText("Completed");
		}
	}

	@FXML
	public void handlePressNext(MouseEvent event) {
		if (_next.getText() == "Completed"){
			System.out.println(_score);
			MainPageController.getUser().updateClassicRecord(_selectedLevel, Integer.toString(_score));

			SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
			load.switchScene("/application/view/ClassicFeedbackPage.fxml");
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
			_rightOrWrong.setVisible(false);
			_youSaid.setVisible(false);
			_answerIs.setVisible(false);
		}
	}

	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/ClassicMenuPage.fxml");
	}



	@Override
	public void collectResult() {
		 _qs.collectResult(_q.getResult());
		 if (_q.getResult()){
		 	_score++;
		 }
	}

	public static String getLevel(){
		return _selectedLevel;
	}

	public static String getScore(){
		return Integer.toString(_score);
	}
}
