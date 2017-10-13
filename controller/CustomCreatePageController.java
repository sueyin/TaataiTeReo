package application.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.model.User;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomCreatePageController {

	@FXML
	private TextField _name;

	@FXML
	private TextField _equation;

	@FXML
	private TextArea _comment;

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
	private CheckBox _public;

	private ObservableList<ListCell> _data;

	@FXML
	private Stage _quitConfirm;

	@FXML
	public void initialize() {
		_data= FXCollections.observableArrayList ();
		_list.setItems(_data);
	}

	@FXML
	public void handlePressCreate(MouseEvent event) {
		boolean isPublic = _public.isSelected();
		String name = _name.getText();
		if (name.equals("")) {
			//TODO create a pop up notification saying must enter a name before create
			System.out.println("please enter a name");
		}
		else {
			if (_data.isEmpty()) {
				//TODO create a pop up notification to ensure if the use want to create an empty question suite
				System.out.println("the question list is empty, do you still want to continue?");
			}
			else {
				//TODO store _data in the file and report question suite created. 
				System.out.println("question suite created");
			}
		}
	}

	@FXML
	public void handlePressAdd(MouseEvent event) {
		String equation = _equation.getText();
		//TODO check if equation is valid to be added into the list
		System.out.println("equation");
		//if valid equation, then add it to data
		_data.add(new ListCell(equation));
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

