package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import application.viewModel.SceneSwitch;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TutorialPageController {
	@FXML
	private JFXButton _right1;

	@FXML
	private JFXButton _right2;
	@FXML
	private JFXButton _left1;

	@FXML
	private JFXButton _right3;
	@FXML
	private JFXButton _left2;
	
	@FXML
	private AnchorPane _page1;
	@FXML
	private AnchorPane _page2;
	@FXML
	private AnchorPane _page3;
	@FXML
	private AnchorPane _page4;
	
	
	@FXML
	public void initialize() {
		_page2.setVisible(false);
		_page3.setVisible(false);
		_page4.setVisible(false);
	}
	
	@FXML
	public void handlePressButton1(MouseEvent event) {
		System.out.println("p");
		_page1.setVisible(false);
		_page2.setVisible(true);
	}
	
	@FXML
	public void handlePressButton2(MouseEvent event) {
		_page2.setVisible(false);
		_page3.setVisible(true);
	}
	
	@FXML
	public void handlePressButton3(MouseEvent event) {
		_page3.setVisible(false);
		_page4.setVisible(true);
	}
	
	@FXML
	public void handlePressLeft1(MouseEvent event) {
		_page1.setVisible(true);
		_page2.setVisible(false);
	}
	
	@FXML
	public void handlePressLeft2(MouseEvent event) {
		_page2.setVisible(true);
		_page3.setVisible(false);
	}
}
