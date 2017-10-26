package application.viewmodel;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * this class is responsible for all scene switches and language switches
 * @author shenhong
 *
 */
public class SceneSwitch {
	private Stage _stage;
	private static Locale _locale = new Locale("en");  // English is the default language
	
	public SceneSwitch(Stage stage) {
		_stage = stage;
	}
	
	/**
	 * setLanguage is called when user choose a different language from what is currently showing
	 * @param lang
	 */
	public static void setLanguage(String lang) {
		if (lang.equals("English")){
			_locale = new Locale("en");
		}
		else if (lang.equals("中文")) {
			_locale = new Locale("zh");
		}
	}
	
	/**
	 * This method is called whenever need to switch scene, @param file is the FXML switching to 
	 */
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
	
	/**
	 * This method returns the current language resource bundle for text reference
	 * @return
	 */
	public static ResourceBundle getBundle() {
		ResourceBundle bundle = ResourceBundle.getBundle("application/lang.bundle", _locale);
		return bundle;
	}

}
