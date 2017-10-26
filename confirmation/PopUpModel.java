package application.confirmation;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is used to create a information pop up, any pop up window that displays a message, and a button to close the window 
 *
 */
public class PopUpModel{

	private Stage _parentStage;
	private Stage _stage;
	private String _text;
	
	@FXML
	private Label _message;
	
	public PopUpModel(Stage parentStage, String text) {
		_parentStage = parentStage;
		_text = text;
	}
	
	public void createPopUp(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/confirmation/PopUp.fxml"));
			PopUpController controller = new PopUpController(_text);
			loader.setController(controller);
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			_stage = new Stage();
			_stage.setScene(scene);
			_stage.initOwner(_parentStage);
			_stage.initModality(Modality.WINDOW_MODAL);

			_stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
