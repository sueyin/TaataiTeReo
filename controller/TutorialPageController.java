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
import javafx.stage.Stage;

public class TutorialPageController {
	@FXML
	private Label _label1;
	@FXML
	private ImageView _img1;
	@FXML
	private JFXButton _right1;
	@FXML
	private ImageView _img2;
	@FXML
	private Label _label2;
	@FXML
	private JFXButton _right2;
	@FXML
	private JFXButton _left1;
	@FXML
	private ImageView _img3;
	@FXML
	private Label _label31;
	@FXML
	private JFXButton _right3;
	@FXML
	private JFXButton _left2;
	@FXML
	private Label _label32;
	@FXML
	private Label _label33;
	
	@FXML
	public void initialize() {
		setFirstPage(true);
		setSecondPage(false);
		setThirdPage(false);
	}
	
	@FXML
	public void handlePressButton1(MouseEvent event) {
		setFirstPage(false);
		setSecondPage(true);
	}
	
	@FXML
	public void handlePressButton2(MouseEvent event) {
		setSecondPage(false);
		setThirdPage(true);
	}
	
	@FXML
	public void handlePressButton3(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/TutorialTestPage.fxml");
	}
	
	@FXML
	public void handlePressLeft1(MouseEvent event) {
		setFirstPage(true);
		setSecondPage(false);
	}
	
	@FXML
	public void handlePressLeft2(MouseEvent event) {
		setSecondPage(true);
		setThirdPage(false);
	}

	private void setFirstPage(boolean show) {
		_label1.setVisible(show);
		_img1.setVisible(show);
		_right1.setVisible(show);
	}
	
	private void setSecondPage(boolean show) {
		_label2.setVisible(show);
		_img2.setVisible(show);
		_right2.setVisible(show);
		_left1.setVisible(show);
	}
	
	private void setThirdPage(boolean show) {
		_label31.setVisible(show);
		_label32.setVisible(show);
		_label33.setVisible(show);
		_img3.setVisible(show);
		_right3.setVisible(show);
		_left2.setVisible(show);
	}
}
