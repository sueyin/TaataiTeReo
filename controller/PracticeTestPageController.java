package application.controller;

import com.jfoenix.controls.JFXButton;

import application.model.question.Question;
import application.model.question.TwoChancesQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PracticeTestPageController extends TestPageController{

	
	@FXML
	private Label _question;
	@FXML
	private JFXButton _next;
	@FXML
	private JFXButton _statistic;
	
	@FXML
	public void initialize() {	
		super.initialize();
		String question = PracticePageController.getQuestion();
		_question.setText(question);
		_next.setVisible(false);
		_statistic.setVisible(false);
		_q = new TwoChancesQuestion(question,question, this);
		
	}
	
	
	/**
	 *This method handles event when the button is pressed (button => "record", "next question")
	 */
	@FXML
	public void handlePressRecord(MouseEvent event) {
		super.handlePressRecord(event);
		System.out.println("after super");
	}
	
	@FXML
	public void handlePressStatistic(MouseEvent event) {
		
	}
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		
	}
	
	@FXML
	public void handlePressNext(MouseEvent event) {
		
	}
}
