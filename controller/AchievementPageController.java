package application.controller;

import application.viewModel.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AchievementPageController {
	@FXML
	private Label _star;
	@FXML
	private Label _highScore;
	@FXML
	private Label _exp;
	@FXML
	private ProgressBar _expBar;

	// Event Listener on JFXButton.onMouseClicked
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
}
