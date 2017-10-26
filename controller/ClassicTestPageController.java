package application.controller;


import application.model.question.TwoChancesQuestion;
import application.model.question.ClassicQuestionSuite;
import application.viewModel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXRadioButton;

public class ClassicTestPageController extends TestPageController {

	@FXML
	private JFXRadioButton _q1;

	@FXML
	private VBox _questionProgress;

	private List<JFXRadioButton> nodeList = new ArrayList<JFXRadioButton>();


	private static String _selectedLevel;

	private ClassicQuestionSuite _qs;



	@FXML
	public void initialize() {
		
		super.initialize();
		//Decide the which level the user chose
		_selectedLevel = ClassicMenuPageController.getSelectedLevel();

		//Generate the corresponding question bank
		_qs = new ClassicQuestionSuite(_selectedLevel);

		//Set the first question
		_q = new TwoChancesQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
		_question.setText(_qs.getQuestion());
		_score = 0;

		//Initialize GUI
		if (Integer.parseInt(_selectedLevel) == 15){
			//Change font size for Strings
			_question.setStyle("-fx-font-size: 24");
		}else if (Integer.parseInt(_selectedLevel) > 11){
			//Change font size for numbers
			_question.setStyle("-fx-font-size: 50");
		}
		
		//configure the labels and buttons 
		_topRight.setText(SceneSwitch.getBundle().getString("keyLevel") +" "+_selectedLevel);
		_q1.setId("onQuestion");
		_next.setVisible(false);
		
		for (Node b: _questionProgress.getChildren()) {
			nodeList.add((JFXRadioButton) b);
		}
	}
	

	/**
	 *This method handles event when the button is pressed (button => "record")
	 */
	@FXML
	public void handlePressRecord(MouseEvent event) {
		super.handlePressRecord(event);
		//if user is on the 10th question, then switch the button text to complete
		if (_qs.getIndexNumber() == 10){
			_next.setText(SceneSwitch.getBundle().getString("keyCompleted"));
		}
	}

	/**
	 *This method handles event when the button is pressed (button => "next question")
	 */
	@FXML
	public void handlePressNext(MouseEvent event) {
		_qs.collectResult(_q.getResult());
		// if the user has ended the 10th question, then switch to result page
		if (_qs.getIndexNumber()==10){
			//record the result of this level 
			MainPageController.getUser().updateClassicRecord(_selectedLevel, Integer.toString(_score));
			//switch to result page 
			SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
			load.switchScene("/application/view/ClassicFeedbackPage.fxml");
		}else {
			//update the GUI showing the question the user is currently on
			boolean result = _q.getResult();
			int index = _qs.getIndexNumber();
			// if previous question is correct, then display it as correct on the side bar 
			if (result) {
				nodeList.get(index-1).setSelected(true);
				nodeList.get(index-1).setId("radio_correct");
				if (index < 10) {
					nodeList.get(index).setId("onQuestion");
				}
			}
			// if previous question is incorrect, then display it as incorrect on the side bar 
			else {
				nodeList.get(index-1).setId("radio_incorrect");
				if (index < 10) {
					nodeList.get(index).setId("onQuestion");
				}
			}
			//Change to the next question
			_qs.nextQuestion();
			_question.setText(_qs.getQuestion());
			_q = new TwoChancesQuestion(_qs.getQuestion(), _qs.getAnswer(), this);
			//Update GUI
			_next.setVisible(false);
			_record.setVisible(true);
			_record.setText(SceneSwitch.getBundle().getString("keyRecord"));
			_rightOrWrong.setVisible(false);
			_youSaid.setVisible(false);
			_answerIs.setVisible(false);
		}
	}

	/**
	 * This method returns to the main page when user presses return 
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		//cancel the recording and compare task 
		super.cancelQuestion();
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/ClassicMenuPage.fxml");
	}

	public static String getLevel(){
		return _selectedLevel;
	}

	public static String getScore(){
		return Integer.toString(_score);
	}
}
