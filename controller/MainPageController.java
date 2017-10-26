package application.controller;

import application.model.admin.User;
import application.viewmodel.SceneSwitch;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainPageController{

	@FXML
	private Label _notification;

	private static User _user = null;
	
	private Button _classic;
	

	
	@FXML
	public void initialize() {
		//display pop up message "welcome user", auto disappear after 5 seconds
		String username = LoginPageController.getSelectedUser();
		_notification.setText(SceneSwitch.getBundle().getString("keyWelcome")+", " + username + "!");
		if (_user == null){
			_user = new User(username);
			FadeTransition ft = new FadeTransition(Duration.seconds(5), _notification);
			ft.setFromValue(1.0);
			ft.setToValue(0.0);
			ft.play();
		}
		else {
			_notification.setVisible(false);
		}
	}
	
	/**
	 * this method changes from main page to classic mode menu page
	 */
	@FXML
	public void handlePressClassic(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/ClassicMenuPage.fxml");
	}
	
	/**
	 * this method changes from main page to survival mode instruction page
	 */
	@FXML
	public void handlePressSurvival(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/SurvivalInstructionPage.fxml");
	}
	
	/**
	 * this method changes from main page to practice page
	 */
	@FXML
	public void handlePressPractice(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticePage.fxml");
	}
	
	/**
	 * this method changes from main page to tutorial page
	 */
	@FXML
	public void handlePressTutorial(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/TutorialPage.fxml");
	}
	
	/**
	 * this method changes from main page to custom instruction page
	 */
	@FXML
	public void handlePressCustom(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/CustomDoPage.fxml");
	}
	
	/**
	 * this method changes from main page to ahievement page
	 */
	@FXML
	public void handlePressAchievement(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/AchievementPage.fxml");
	}
	
	/**
	 * this method changes from main page to login page
	 */
	@FXML
	public void handlePressLogout(MouseEvent event) {
		_user = null;
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/LoginPage.fxml");
	}
	
	/**
	 * this method changes from main page to leader board page 
	 */
	@FXML
	public void handlePressLeaderBoard(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/CustomLeaderBoardPage.fxml");
	}

	/**
	 * Pass the current user
	 * @return the current user
	 */
	public static User getUser(){
		return _user;
	}

}