package application.confirmation;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * this class is a controller for all confirmation pop up windows
 * @author shenhong
 *
 */
public class ConfirmationController {
	
	private String _text, _ok, _cancel;
	
	@FXML
	private Label _message;
	
	@FXML
	private Button _buttonOk;
	
	@FXML
	private Button _buttonCancel;
	
	private boolean _confirm;
	
	/**
	 * This constructor sets up all the texts required for the pop up window
	 * @param text the main message of the pop up 
	 * @param ok is the text displayed on the button with positive confirmation to the message 
	 * @param cancel is the text displayed on the button with negative confirmation to the message
	 */
	public ConfirmationController(String text, String ok, String cancel) {
		_text =text;
		_ok = ok;
		_cancel = cancel;
	}
	
	/**
	 * this methods sets up the text according to values passed in from the constructor
	 */
	@FXML
	public void initialize() {
		_message.setText(_text);
		_buttonOk.setText(_ok);
		_buttonCancel.setText(_cancel);
	}
	
	/**
	 * this class set confirmation to true and close the window 
	 * @param event
	 */
	@FXML
	public void handlePressOk(MouseEvent event) {
		_confirm = true;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	/**
	 * this class set confirmation to false and close the window 
	 * @param event
	 */
	@FXML
	public void handlePressCancel(MouseEvent event) {
		_confirm = false;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	/**
	 *this method returns the user option 
	 * @return
	 */
	public boolean getResult(){
		return _confirm;
	}

}
