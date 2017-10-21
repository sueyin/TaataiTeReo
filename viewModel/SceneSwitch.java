package application.viewModel;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitch {
	private Stage _stage;
	private static Locale _locale = new Locale("en");
	
	public SceneSwitch(Stage stage) {
		_stage = stage;
	}
	
	public static void setLanguage(String lang) {
		if (lang.equals("English")){
			_locale = new Locale("en");
		}
		else if (lang.equals("中文")) {
			_locale = new Locale("zh");
		}
	}
	
	public void switchScene(String file){
	    try {
	        ResourceBundle bundle = ResourceBundle.getBundle("application/lang.bundle", _locale);
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(file),bundle);
	        Parent parent = loader.load();
	    	Scene scene = new Scene(parent);
	    	_stage.setScene(scene);
	    	_stage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
