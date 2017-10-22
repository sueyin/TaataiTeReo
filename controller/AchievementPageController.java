package application.controller;

import application.viewModel.SceneSwitch;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	@FXML
	private HBox _star1;
	@FXML
	private HBox _star2;
	@FXML
	private HBox _null1;
	@FXML
	private HBox _null2;
	
	private boolean[] _medalResult = {false, false, true, true, false, false, false, true};

	@FXML
	public void initialize() {	
		setMedals();
	}
	
	private void setMedals() {
		for (int i = 0; i<4;i++) {
			if(_medalResult[i]) {
				_null1.getChildren().get(i).setVisible(false);
			}
			else {
				_star1.getChildren().get(i).setVisible(false);
			}
			if (_medalResult[i+4]) {
				_null2.getChildren().get(i).setVisible(false);
			}
			else {
				_star2.getChildren().get(i).setVisible(false);
			}
		}
	}



	// Event Listener on JFXButton.onMouseClicked
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
}
