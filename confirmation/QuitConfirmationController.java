package application.confirmation;

import javafx.fxml.FXML;

import com.jfoenix.controls.JFXButton;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class QuitConfirmationController {
	@FXML
	private JFXButton _delete;
	@FXML
	private JFXButton _cancel;
		
	private static boolean _quit;

	// Event Listener on JFXButton[#_delete].onMouseClicked
	@FXML
	public void handlePressConfirmExit(MouseEvent event) {
		_quit = true;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	// Event Listener on JFXButton[#_cancel].onMouseClicked
	@FXML
	public void handlePressConfirmStay(MouseEvent event) {
		_quit = false;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	public static boolean getQuit() {
		return _quit;
	}
}
