package application.controller;

import application.model.question.SurvivalQuestionSuite;
import application.viewModel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SurvivalTestPageController extends TestPageController{

	@FXML
	private Button _record;
	
	@FXML
	private Button _next;
	
	@FXML
	private Button _return;
	
	@FXML
	private Label _question;
	
	@FXML
	private Label _message;

	//Functionality
	private SurvivalQuestionSuite _qs;

	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
	
	@FXML
	public void handlePressNext(MouseEvent event) {

		
	}

	@Override
	public void rightGUI(){

	}


}
