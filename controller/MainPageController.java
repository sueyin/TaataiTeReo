package application.controller;

import java.io.IOException;

import application.model.User;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainPageController {

	@FXML
	private Label _notification;

	private static User _user = null;
	
	@FXML
	public void initialize() {
		
		//display pop up message "welcome user", auto disappear after 3 seconds
		String username = LoginPageController.getSelectedUser();
		_notification.setText("Welcome, " + username + "!");
		if (_user == null){
			_user = new User(username);
			FadeTransition ft = new FadeTransition(Duration.seconds(3), _notification);
			ft.setFromValue(1.0);
			ft.setToValue(0.0);
			ft.play();
		}
	}
	
	/**
	 * this method changes from main page to classic mode menu page
	 */
	@FXML
	public void handlePressClassic(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicMenuPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * this method changes from main page to survival mode instruction page
	 */
	@FXML
	public void handlePressSurvival(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/SurvivalInstructionPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * this method changes from main page to endless mode instruction page
	 */
	@FXML
	public void handlePressEndless(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/EndlessInstructionPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * this method changes from main page to tutorial page
	 */
	@FXML
	public void handlePressTutorial(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/TutorialPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * this method changes from main page to custom instruction page
	 */
	@FXML
	public void handlePressCustom(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/CustomInstructionPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * this method changes from main page to ahievement page
	 */
	@FXML
	public void handlePressAchievement(MouseEvent event) {
        try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/AchievementPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * this method changes from main page to login page
	 */
	@FXML
	public void handlePressLogout(MouseEvent event) {
        try {
			_user = null;
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/LoginPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Pass the current user
	 * @return the current user
	 */
	public static User getUser(){
		return _user;
	}
}