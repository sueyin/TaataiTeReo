package application.controller;

import application.model.User;
import application.model.file.FileReader;
import application.viewModel.SceneSwitch;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;

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
	private VBox _star1;
	@FXML
	private VBox _star2;
	@FXML
	private VBox _null1;
	@FXML
	private VBox _null2;
	
	private boolean[] _medalResult;

	private User _usr;

	private boolean[] _achivs;

	@FXML
	public void initialize() {
		_usr = MainPageController.getUser();
		_achivs = _usr.getAchiv();
		_star.setText(Integer.toString(_usr.getStars()));
		_highScore.setText(Integer.toString(_usr.getSurvivalScore()));
		_exp.setText(Integer.toString(_usr.getExp()));
		checkAchivs();
		_medalResult = _usr.getAchiv();
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

	private void checkAchivs(){
		// * 0.Complete one Level
		if (!_achivs[0]){
			FileReader reader = new FileReader(_usr.getDir() + "classic.txt");
			Map<String, String> classicRec = reader.getData();
			for (String s : classicRec.keySet()){
				if (!classicRec.get(s).equals("-")){
					_usr.unlockAchiv(0);
				}
			}
		}
		// * 1.Complete one Level with 3 stars
		if (!_achivs[1]){
			FileReader reader = new FileReader(_usr.getDir() + "classic.txt");
			Map<String, String> classicRec = reader.getData();
			for (String s : classicRec.keySet()){
				if (classicRec.get(s).equals("9") || classicRec.get(s).equals("10")){
					_usr.unlockAchiv(1);
				}
			}
		}

		// * 2.Collect 45 stars
		if ((!_achivs[2]) && (Integer.parseInt(_star.getText()) == 45)){
			_usr.unlockAchiv(2);
		}

		// * 3.Read 1-99
		if (!_achivs[3]){
			boolean readAll = true;
			Map<String, ArrayList<Boolean>> allRec = _usr.getOverallStatistic();
			for (String s : allRec.keySet()){
				if (allRec.get(s) == null){
					readAll = false;
				}
			}
			if (readAll){
				_usr.unlockAchiv(3);
			}
		}

		// * 4.Survival 50+
		if ((!_achivs[4]) && (_usr.getSurvivalScore() >=50)){
			_usr.unlockAchiv(4);
		}

		// * 5.Upgrade Bronze
		if ((!_achivs[5]) && (Integer.parseInt(_exp.getText()) >= 80 )){
			_usr.unlockAchiv(5);
		}

		// * 6.Upgrade Sliver
		if ((!_achivs[6]) && (Integer.parseInt(_exp.getText()) >= 300 )){
			_usr.unlockAchiv(6);
		}

		// * 7.Upgrade Gold
		if ((!_achivs[7]) && (Integer.parseInt(_exp.getText()) >= 500 )){
			_usr.unlockAchiv(7);
		}
	}

	// Event Listener on JFXButton.onMouseClicked
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
}
