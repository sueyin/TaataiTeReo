package application.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import application.confirmation.QuitConfirmationController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomDoPageController {

	@FXML
	private TableView<TableList> _publicList;
	@FXML
	private TableView<TableList> _privateList;
	@FXML
	private JFXButton _do;
	@FXML
	private JFXButton _edit;
	@FXML
	private JFXButton _delete;
	
	private Stage _popUp;
	
	@FXML
	private Tab _private;
	@FXML
	private Tab _public;
	
	@FXML
	private TabPane _tab;
	
	private TableList _selected;

	private ObservableList<TableList> _publicData= FXCollections.observableArrayList();
	private ObservableList<TableList> _privateData= FXCollections.observableArrayList();

	public void initialize() {
		_privateData = FXCollections.observableArrayList(
				new TableList("a1","b","d","e",false), //TODO import private question suite in this format (name, author, description, number of question, isPublic)
				new TableList("a1","b","d","e",false),
				new TableList("a1","b","d","e",false)
				);
		_publicData = FXCollections.observableArrayList(
				new TableList("a2","b","d","e",true),//TODO import public question suite in this format (name, author, description, number of question)
				new TableList("a2","b","d","e",true),
				new TableList("a2","b","d","e",true)
				);
		setTable(_publicList, _publicData);
		setTable(_privateList, _privateData);
	}

	private void setTable(TableView table, ObservableList<TableList> data) {
		table.setEditable(true);
		table.setItems(data);
		TableColumn<TableList, String> nameCol = new TableColumn<TableList, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn<TableList, String> authorCol = new TableColumn<TableList, String>("Author");
		authorCol.setCellValueFactory(new PropertyValueFactory("author"));
		TableColumn<TableList, String> descriptionCol = new TableColumn<TableList, String>("Description");
		descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
		TableColumn<TableList, String> questionNumCol = new TableColumn<TableList, String>("Number of Questions");
		questionNumCol.setCellValueFactory(new PropertyValueFactory("questionNum"));

		table.getColumns().setAll(nameCol, authorCol, descriptionCol, questionNumCol);
	}
	
	private void setSelected() {
		_selected = null;
		int t = _tab.getSelectionModel().getSelectedIndex();
		if (t==0){
			_selected = _publicList.getSelectionModel().getSelectedItem();
			
		}
		else {
			_selected = _privateList.getSelectionModel().getSelectedItem();
			
		}
		if (_selected == null) {
			System.out.println("select one first");
			//TODO pop up notification need to select one first
		}
	}
	// Event Listener on JFXButton[#_do].onMouseClicked
	@FXML
	public void handlePressDo(MouseEvent event) {
		setSelected();
		if (_selected == null) {
			//TODO pop up window
		}
		else {
			//TODO switch to question list 
		}
	}

	// Event Listener on JFXButton[#_edit].onMouseClicked
	@FXML
	public void handlePressEdit(MouseEvent event) {
		setSelected();
		if (_selected == null) {
			System.out.println("null.......");
			//TODO pop up window
		}
		else {
			//TODO switch to the selected question list's edit page
		}
	}

	// Event Listener on JFXButton[#_delete].onMouseClicked
	@FXML
	public void handlePressDelete(MouseEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/application/confirmation/DeleteTableListConfirmation.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(parent);
			_popUp = new Stage();
			_popUp.setScene(scene);
			_popUp.initOwner(stage);
			_popUp.initModality(Modality.WINDOW_MODAL);

			_popUp.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//get user result, if the user does want to delete, then delete
		boolean confirm = application.confirmation.DeleteTableListConfirmationController.getDeleteConfirm();
		System.out.println(""+confirm);
		if (confirm){
			setSelected();
			if (_selected==null){
				System.out.println("null.......");
				//TODO pop up 
			}
			else {
				boolean isPublic = _selected.getPublic();
				System.out.println("isPublic "+isPublic);
				if (isPublic){
					System.out.println("public");
					_publicData.remove(_selected);
					//TODO remove selected question suite (Public)
				}
				else {
					System.out.println("private");
					_privateData.remove(_selected);
					//TODO remove selected question suite (private)
				}
			}

		}
	}
}

