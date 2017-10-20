package application.controller;

import application.model.question.PracticeQuestion;
import application.model.question.Question;
import application.model.question.TwoChancesQuestion;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PracticeTestPageController extends TestPageController{

	private Question _q;
	
	@FXML
	public void initialize() {
		String _question = PracticePageController.getQuestion();
		_q = new PracticeQuestion(_question, this);
	}
	
	
	/**
	 *This method handles event when the button is pressed (button => "record", "next question")
	 */
	@FXML
	public void handlePressRecord(MouseEvent event) {
		super.handlePressRecord(event);
	}
}
