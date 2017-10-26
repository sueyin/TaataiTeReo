package application.controller;

import application.viewmodel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SurvivalInstructionPageController {

	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
	
	@FXML
	public void handlePressStart(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/SurvivalTestPage.fxml");
	}
	
}
