package application.controller;

import java.io.IOException;

import application.viewModel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
	
	@FXML
	public void handlePressNext(MouseEvent event) {
		
	}

	@Override
	public void collectResult() {

	}
}
