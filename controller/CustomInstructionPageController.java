package application.controller;

import java.io.IOException;

import application.model.CustomManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomInstructionPageController {
	private static CustomManager _manager;

	@FXML
	public void initialize() {
		_manager = new CustomManager(MainPageController.getUser());
	}

	public static CustomManager getManager(){
		return _manager;
	}

	@FXML
	public void handlePressReturn(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/MainPage.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handlePressCreate(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/CustomCreatePage.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handlePressDo(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/CustomDoPage.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handlePressLeaderBoard(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/CustomLeaderBoardPage.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
