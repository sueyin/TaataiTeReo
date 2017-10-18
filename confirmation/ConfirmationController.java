package application.confirmation;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConfirmationController {
	
	private String _text, _ok, _cancel;
	
	@FXML
	private Label _message;
	
	@FXML
	private Button _buttonOk;
	
	@FXML
	private Button _buttonCancel;
	
	private boolean _confirm;
	
	public ConfirmationController(String text, String ok, String cancel) {
		_text =text;
		_ok = ok;
		_cancel = cancel;
	}
	
	@FXML
	public void initialize() {
		_message.setText(_text);
		_buttonOk.setText(_ok);
		_buttonCancel.setText(_cancel);
	}
	@FXML
	public void handlePressOk(MouseEvent event) {
		_confirm = true;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	@FXML
	public void handlePressCancel(MouseEvent event) {
		_confirm = false;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	public boolean getResult(){
		return _confirm;
	}

}
