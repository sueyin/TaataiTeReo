package application.viewModel;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitch {
	private Stage _stage;
	
	public SceneSwitch(Stage stage) {
		_stage = stage;
	}
	
	public void switchScene(String file){
	    try {
	    	Parent parent = FXMLLoader.load(getClass().getResource(file));
	    	Scene scene = new Scene(parent);
	    	_stage.setScene(scene);
	    	_stage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
