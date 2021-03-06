package application.confirmation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * this class is a controller for all information pop up windows
 *
 */

public class PopUpController {

	private String _text;

	@FXML
	private Label _message;

	//takes in the message to be displayed as parameter
	public PopUpController(String text) {
		_text =text;

	}
	@FXML
	public void initialize() {
		_message.setText(_text);
	}
	@FXML
	public void handlePressConfirm(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();	
	}

}
