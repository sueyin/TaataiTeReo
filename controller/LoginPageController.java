package application.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LoginPageController {
	
	@FXML
	public void handlePressAddUser(MouseEvent event) {
		System.out.println("add user");
	}
	
	@FXML
	public void handlePressDeleteUser(MouseEvent event) {
		System.out.println("delete user");
	}

	@FXML
	public void handlePressEnter(MouseEvent event) {
		System.out.println("enter");
	}
}
