package application.confirmation;

import java.io.File;

import application.TataiApp;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DeleteTableListConfirmationController {
	
	private static boolean _toDelete;
	
	@FXML
	public void handlePressConfirmDelete(MouseEvent event) {
		_toDelete = true;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	@FXML
	public void handlePressConfirmCancel(MouseEvent event) {
		_toDelete = false;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	public static boolean getDeleteConfirm(){
		return _toDelete;
	}
}
