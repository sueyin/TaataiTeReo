package application.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import application.confirmation.ConfirmationModel;
import application.confirmation.PopUpModel;
import application.model.CustomManager;
import application.viewModel.SceneSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.jfoenix.controls.JFXButton;

public class CustomCreatePageController {


	@FXML
	private TextField _equation;

	@FXML
	private ListView<ListCell> _list;

	@FXML
	private JFXButton _preview;

	@FXML
	private JFXButton _add;

	@FXML
	private JFXButton _create;

	@FXML
	private JFXButton _return;

	@FXML
	private TextField _description;
	
	@FXML
	private CheckBox _public;
	
	@FXML
	private Label _warningMessage;
	
	@FXML
	private Label _equationMessage;
	
	@FXML
	private JFXButton _help;
	
	@FXML
	private Label _helpPanel;
	
	@FXML
	private Label _maxMessage;

	private ObservableList<ListCell> _data;

	private String[] _qs;

	private String _id;

	private CustomManager _manager = CustomDoPageController.getManager();

	@FXML
	private Stage _popUp;

	@FXML
	public void initialize() {
		//set up list view
		_data= FXCollections.observableArrayList ();
		_list.setItems(_data);
		
		//disable warning message initially
		_warningMessage.setVisible(false);
		_equationMessage.setVisible(false);

		//generate ID for this new created question suite
		_qs = new String[10];
		for (int i = 0; i<_qs.length; i++){
			_qs[i] = "";
		}
		generateID();
		
		
		//set up help button, every time mouse hover over it, the help manual appears
		_helpPanel.setVisible(false);
		_maxMessage.setVisible(false);
		_help.hoverProperty().addListener((ov, oldValue, newValue) -> {
		    if (newValue) {
		        _helpPanel.setVisible(true);
		    } else {
		    	 _helpPanel.setVisible(false);
		    }
		});
	}

	/**
	 * When user press create, checks if the input is valid, if so, create question suite and then return to menu page, if not, display warning
	 */
	@FXML
	public void handlePressAdd(MouseEvent event) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		//Ensure the given input is not empty
		String equation = _equation.getText();
		if (equation.length() < 1){
			//Empty input notification
			errorMsg(SceneSwitch.getBundle().getString("keyEmptyEquation"));
		}else {
			boolean validEquation = true;
			int value = -1;
			try {
				value = Integer.parseInt(engine.eval(equation).toString());
			} catch (ScriptException e) {
				validEquation = false;
				// Wrong format, equation can not be evaluated
				errorMsg(SceneSwitch.getBundle().getString("keyWrongEquation"));
			} catch (NumberFormatException e) {
				validEquation = false;
				//Answer is not an integer
				errorMsg("Please ensure the answer is an integer");
			}
			if (validEquation) {
				if (value < 1 || value > 99) {
					errorMsg(SceneSwitch.getBundle().getString("keyOutofRange"));
				} else {
					//If valid equation, then add it to data
					//int index = _data.size() + 1;
					_qs[_data.size()] = value + "#" + equation;
					//TODO format is 32#2+30
					_data.add(new ListCell(equation));
					//set text field to empty again 
					_equation.setText("");
				}
				if (_data.size()==10){
					//when the number of questions reachs maximum, disable add button
					_add.setVisible(false);
					_maxMessage.setVisible(true);
				}
			}
		}
	}

	@FXML
	public void handlePressCreate(MouseEvent event) {
		String description = _description.getText();
		if (description.equals("")) {
			errorMsg(SceneSwitch.getBundle().getString("keyNoDescription"));
		} else {
			if (_data.isEmpty()) {
				errorMsg(SceneSwitch.getBundle().getString("keyEmptyQuestionSuite"));
			}
			else {
				//TODO confirmation ask
				//Store _data in the file and report question suite created.
				ArrayList<String> delivery = new ArrayList<>();
				for (String s : _qs){
					if (s.length() > 0){
						delivery.add(s);
					}
				}
				//Create a new Question suite
				_manager.writeCustomSuite(_id, description, delivery, _public.isSelected());

				//Update GUI
				//Return to custom page
				Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				SceneSwitch load = new SceneSwitch(currentStage);
				load.switchScene("/application/view/CustomDoPage.fxml");
				//Successfully created message
				PopUpModel popUp = new PopUpModel(currentStage, SceneSwitch.getBundle().getString("keySuccessQuestionList"));
				popUp.createPopUp();
			}
		}
	}


	@FXML
	public void handlePressReturn(MouseEvent event) {
		//opens a window that confirms if the user wants to quit 
		
		ConfirmationModel confirmation = new ConfirmationModel((Stage) ((Node) event.getSource()).getScene().getWindow(),SceneSwitch.getBundle().getString("keySureReturn"), SceneSwitch.getBundle().getString("keyReturn"), SceneSwitch.getBundle().getString("keyStay"));
		boolean confirm = confirmation.createPopUp();
		if (confirm){
			SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
			load.switchScene("/application/view/CustomDoPage.fxml");
		}
	}

	class ListCell extends HBox {
		Label label = new Label();
		Button delete = new Button("delete");

		ListCell(String equation) {
			super();
			label.setText(equation);
			label.setMaxWidth(Double.MAX_VALUE);
			label.setTextFill(Color.WHITE);
			HBox.setHgrow(label, Priority.ALWAYS);

			delete.setOnMouseClicked(this::clickDelete);
			delete.setStyle("-fx-text-fill: white");
			

			this.getChildren().addAll(label, delete);
		}

		public void clickDelete(MouseEvent event) {
			//TODO get the row number and remove the corresponding question
			_qs[_data.indexOf(this)]= "";
			_data.remove(this);
			_add.setVisible(true);
			_maxMessage.setVisible(false);
		}

	}






	/*
    Support methods
 */
	private void errorMsg(String msg){
		Service delay = new TimedMessage();
		_equationMessage.setText(msg);
		_equationMessage.setVisible(true);
		//disable the message after a few seconds
		if (!delay.isRunning()){
			delay.start();
		}
		delay.setOnSucceeded(eg -> {
			_equationMessage.setVisible(false);
			delay.reset();
		});
	}

	/**
	 * Generate a unique ID
	 */
	private void generateID(){
		String root = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		String id = "";
		for (int i = 0; i < 7; i++) {
			id = id + root.toCharArray()[(int) (Math.random() * (root.length()))];
		}
		//Recursively check if the id is unique
		if ((_manager.getPublicSuites().keySet().contains(id))
			|| (_manager.getPrivateSuites().keySet().contains(id))){
			generateID();
		}
		_id = id;
	}
}


