package application.confirmation;

import java.io.File;

import application.TataiApp;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomCreateSuccessConfirmationController {
	
	@FXML
	public void handlePressConfirm(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}

}
