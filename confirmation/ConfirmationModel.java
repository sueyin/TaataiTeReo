package application.confirmation;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * This class is used to create a confirmation pop up, any pop up window with two options (eg. ok and cancel)
 *
 */
public class ConfirmationModel {

	private Stage _parentStage;
	private Stage _stage;
	private String _text, _ok, _cancel;
	
	@FXML
	private Label _message;
	
	public ConfirmationModel(Stage parentStage, String text, String ok, String cancel) {
		_parentStage = parentStage;
		_text = text;
		_ok = ok;
		_cancel = cancel;
	}
	
	/**
	 * this method is called when need to create a pop up window 
	 * @return
	 */
	public boolean createPopUp(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/confirmation/Confirmation.fxml"));
			//creates a new window and controller 
			ConfirmationController controller = new ConfirmationController(_text,_ok,_cancel);
			loader.setController(controller);
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			_stage = new Stage();
			_stage.setScene(scene);
			_stage.initOwner(_parentStage);
			_stage.initModality(Modality.WINDOW_MODAL);

			_stage.showAndWait();
			
			return controller.getResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
