package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SurvivalTestPageController {

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
	public void handlePressRecord(MouseEvent event) {
		
	}
	
	@FXML
	public void handlePressNext(MouseEvent event) {
		
	}
}
