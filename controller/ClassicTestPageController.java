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
		_question.setStyle("-fx-text-size: 100");

		if (Integer.parseInt(_selectedLevel) > 12){
			//Change font size for Strings
			_question.setStyle("-fx-font-size: 24");
		}else{
			//Change font size for numbers
			_question.setStyle("-fx-font-size: 50");
		}
		_topRight.setText("Level "+_selectedLevel);

		_q1.setId("onQuestion");
		_record.setVisible(true);
		_record.setText("Record");
		_next.setVisible(false);
		_next.setText("Next");
		
		for (Node b: _questionProgress.getChildren()) {
			nodeList.add((JFXRadioButton) b);
		}
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
		_qs.collectResult(_q.getResult());
		if (_next.getText().equals("Completed")){
			MainPageController.getUser().updateClassicRecord(_selectedLevel, Integer.toString(_score));

			SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
			load.switchScene("/application/view/ClassicFeedbackPage.fxml");
		}else {
			//update the GUI showing the question the user is currently on
			boolean result = _q.getResult();
			int index = _qs.getIndexNumber();
			if (result) {
				nodeList.get(index-1).setSelected(true);
				nodeList.get(index-1).setId("radio_correct");
				if (index < 9) {
					nodeList.get(index).setId("onQuestion");
				}
			}
			else {
				nodeList.get(index-1).setId("radio_correct");
				if (index < 9) {
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

	public static String getLevel(){
		return _selectedLevel;
	}

	public static String getScore(){
		return Integer.toString(_score);
	}
}
