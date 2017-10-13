package application.controller;


import java.io.IOException;
import java.util.ArrayList;

import application.model.CustomManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CustomCreatePageController {

	@FXML
	private TextField _name;

	@FXML
	private TextField _equation;

	@FXML
	private ListView<ListCell> _list;

	@FXML
	private Button _preview;

	@FXML
	private Button _add;

	@FXML
	private Button _create;

	@FXML
	private Button _return;

	@FXML
	private TextField _description;
	
	@FXML
	private CheckBox _public;

	private ObservableList<ListCell> _data;

	private ArrayList<String> _qs = new ArrayList<>();

	private CustomManager _manager;

	private int _questionIndex;


	@FXML
	private Stage _quitConfirm;

	@FXML
	public void initialize() {
		_data= FXCollections.observableArrayList ();
		_list.setItems(_data);
		_questionIndex = 0;
		_manager = new CustomManager(MainPageController.getUser());
	}

	@FXML
	public void handlePressCreate(MouseEvent event) {
		_questionIndex = 0;
		String name = _name.getText();
		if (name.equals("")) {
			//TODO create a pop up notification saying must enter a name before create
			System.out.println("please enter a name");
		}
		else {
			if (_data.isEmpty()) {
				//TODO create a pop up notification to ensure if the use want to create an empty question suite
				System.out.println("the question list is empty, 你四不四撒");
			}
			else {
				//TODO store _data in the file and report question suite created. 
				System.out.println("question suite created");
				String id = name+"#"+ _description.getText()+"#"+_quitConfirm;
				if (_public.isSelected()){
					//Create a Public Question suite
					_manager.writePublicSuite(id, _qs);
				}else{
					//Create a Private Question suite
					_manager.writePrivateSuite(id, _qs);
				}
			}
		}
		//handlePressReturn(event);

	}

	@FXML
	public void handlePressAdd(MouseEvent event) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
			//Ensure the given input is not empty
			String equation = _equation.getText();
			if (equation.length() > 0){
				try {
				String value = engine.eval(equation).toString();
				//Ensure the given input is within the range
				if((Integer.parseInt(value) < 1)||(Integer.parseInt(value) > 99)){
						//TODO out of range notification
				}else{
					_questionIndex++;
					//If valid equation, then add it to data
					_qs.add(_questionIndex + "#" + value + "#"+ equation);
					//TODO format is 1#32#2+30
					_data.add(new ListCell(equation));
				}
				} catch (ScriptException e) {
					e.printStackTrace();
					//TODO wrong format exception
				}
			}else{
				//TODO empty input notification
			}
	}

	@FXML
	public void handlePressReturn(MouseEvent event) {
		//opens a window that confirms if the user wants to quit 
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/QuitConfirmation.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(parent);
			_quitConfirm = new Stage();
			_quitConfirm.setScene(scene);
			_quitConfirm.initOwner(stage);
			_quitConfirm.initModality(Modality.WINDOW_MODAL);
			_quitConfirm.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//get user result, if the user does want to quit, then quit
		boolean confirm = QuitConfirmationController.getQuit();
		if (confirm){
			try {
	        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/CustomInstructionPage.fxml"));
	        	Scene scene = new Scene(parent);
	        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        	stage.setScene(scene);
	        	stage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}

	class ListCell extends HBox {
		Label label = new Label();
		Button delete = new Button();
		Button preview = new Button();



		ListCell(String equation) {
			super();
			label.setText(equation);
			label.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(label, Priority.ALWAYS);

			delete.setOnMouseClicked(this::clickDelete);
			delete.setText("delete");

			preview.setOnMouseClicked(this::clickPreview);
			preview.setText("preview");

			this.getChildren().addAll(label, delete, preview);
		}

		public void clickDelete(MouseEvent event) {
			System.out.println("delete");
			_data.remove(this);
		}

		public void clickPreview(MouseEvent event) {
			System.out.println("preview");
		}
	}
}

