<<<<<<< HEAD
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

	private User _user = null;
	
	@FXML
	public void initialize() {
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
}
=======
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

	private User _user = null;
	
	@FXML
	public void initialize() {
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
}
>>>>>>> 92d8391408b64abdb212da722f4b465ba2ae5f38
