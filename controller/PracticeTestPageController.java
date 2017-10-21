package application.controller;

import com.jfoenix.controls.JFXButton;

import application.model.question.Question;
import application.model.question.TwoChancesQuestion;
import application.viewModel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
	}
	
	@FXML
	public void handlePressStatistic(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeStatisticPage.fxml");
	}
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticePage.fxml");
	}
	
	@FXML
	public void handlePressNext(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeTestPage.fxml");
	}
	
	@Override
	public void rightGUI() {
		super.rightGUI();
		_statistic.setVisible(true);
	}
	
	@Override
	public void wrongGUI() {
		super.wrongGUI();
		_statistic.setVisible(true);
	}
	
	@Override
	public void tryAgainGUI() {
		super.tryAgainGUI();
	}



	
	private void collectResult(){

	}
}
