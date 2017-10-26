package application.controller;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import application.viewmodel.SceneSwitch;
import javafx.scene.Node;

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
	private AnchorPane _page5;
	@FXML
	private AnchorPane _page6;
	
	private List<AnchorPane> pages;
	
	private int _currentPage;
	
	@FXML
	public void initialize() {
		//initialize to put all pages into array list, and set initial status to be at position 0 
		_currentPage = 0;
		pages = new ArrayList(6);
		pages.add(_page1);
		pages.add(_page2);
		pages.add(_page3);
		pages.add(_page4);
		pages.add(_page5);
		pages.add(_page6);
		for (AnchorPane p: pages) {
			p.setVisible(false);
		}
		//only set page 1 to be visible
		_page1.setVisible(true);
		
	}
	
	/**
	 * every time user press next, the scene switch to the next anchor pane in the array list
	 */
	@FXML
	public void handlePressButton(MouseEvent event) {
		pages.get(_currentPage+1).setVisible(true);
		pages.get(_currentPage).setVisible(false);
		_currentPage++;
	}
	
	/**
	 * every time user press back, the scene switch to the previous anchor pane in the array list
	 */
	@FXML
	public void handlePressLeft(MouseEvent event) {
		pages.get(_currentPage).setVisible(false);
		pages.get(_currentPage-1).setVisible(true);
		_currentPage--;
	}

	/**
	 * When user press "finish tutorial", switch back to main page
	 */
	@FXML
	public void handlePressFinish(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
	
	/**
	 * Switch to main page when user press return
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
}
