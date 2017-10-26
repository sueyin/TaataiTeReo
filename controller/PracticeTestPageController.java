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
	private Label _statisticLabel;
	
	@FXML
	public void initialize() {	
		super.initialize();
		String question = PracticePageController.getQuestion();
		_question.setText(question);
		_next.setVisible(false);
		_statistic.setVisible(false);
		_statisticLabel.setVisible(false);
		_topRight.setVisible(false);
		_q = new TwoChancesQuestion(question,question, this);
	}
	
	
	/**
	 *This method handles event when the button is pressed (button => "record", "next question")
	 */
	@FXML
	public void handlePressRecord(MouseEvent event) {
		super.handlePressRecord(event);
	}
	
	/**
	 * this method switches to statistic page
	 * @param event
	 */
	@FXML
	public void handlePressStatistic(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeStatisticPage.fxml");
	}
	
	/**
	 * this method switch to practice page 
	 * @param event
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		super.cancelQuestion();
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticePage.fxml");
	}
	
	/**
	 * this page repeats the current question again
	 * @param event
	 */
	@FXML
	public void handlePressNext(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticeTestPage.fxml");
	}
	
	/**
	 * this method calls the rightGUI() method in TestPageControler and also format the statistic button
	 */
	@Override
	public void rightGUI() {
		super.rightGUI();
		MainPageController.getUser().updatePractiseRecord(_q.getQuestion(), _q.getResult());
		_statistic.setVisible(true);
		_statisticLabel.setVisible(true);
	}
	
	/**
	 * this method calls the wrongGUI() method in TestPageControler and also format the statistic button
	 */
	@Override
	public void wrongGUI() {
		super.wrongGUI();
		MainPageController.getUser().updatePractiseRecord(_q.getQuestion(), _q.getResult());
		_statistic.setVisible(true);
		_statisticLabel.setVisible(true);
	}
	
	/**
	 * this method calls the tryAgainGUI() method in TestPageControler
	 */
	@Override
	public void tryAgainGUI() {
		super.tryAgainGUI();
	}
}
