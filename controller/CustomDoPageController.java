package application.controller;

import application.confirmation.ConfirmationModel;
import application.model.CustomManager;
import application.tableModel.TableList;
import application.viewModel.SceneSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.jfoenix.controls.JFXButton;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
	private JFXButton _delete;

	private Stage _popUp;

	@FXML
	private Tab _private;
	@FXML
	private Tab _public;

	@FXML
	private TabPane _tab;
	
	@FXML
	private Label _message;

	private static TableList _selected;

	private ObservableList<TableList> _publicData= FXCollections.observableArrayList();
	private ObservableList<TableList> _privateData= FXCollections.observableArrayList();

	//Grace's part
	private static CustomManager _manager;
	
	public static CustomManager getManager() {
		return _manager;
	}

	@FXML
	public void initialize() {
		_manager = new CustomManager();
		
		_privateData = FXCollections.observableArrayList();
		Map<String, ArrayList<String>> privateSuites =  _manager.getPrivateSuites();
		for (String s : privateSuites.keySet()){
			String author = privateSuites.get(s).get(0);
			String desp = privateSuites.get(s).get(1);
			String total = privateSuites.get(s).get(2);
			TableList object = new TableList (s, author, desp, total, false);
			_privateData.add(object);
		}
		_publicData = FXCollections.observableArrayList();
		Map<String, ArrayList<String>> publicSuites =  _manager.getPublicSuites();
		for (String s : publicSuites.keySet()){
			String author = publicSuites.get(s).get(0);
			String desp = publicSuites.get(s).get(1);
			String total = publicSuites.get(s).get(2);
			TableList object = new TableList (s, author, desp, total, true);
			_publicData.add(object);
		}
		setTable(_publicList, _publicData);
		setTable(_privateList, _privateData);
		
		_message.setVisible(false);
	}

	private void setTable(TableView table, ObservableList<TableList> data) {
		table.setEditable(true);
		table.setItems(data);
		TableColumn<TableList, String> nameCol = new TableColumn<TableList, String>(SceneSwitch.getBundle().getString("keyName"));
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn<TableList, String> authorCol = new TableColumn<TableList, String>(SceneSwitch.getBundle().getString("keyAuthor"));
		authorCol.setCellValueFactory(new PropertyValueFactory("author"));
		TableColumn<TableList, String> descriptionCol = new TableColumn<TableList, String>(SceneSwitch.getBundle().getString("keyDescription"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
		TableColumn<TableList, String> questionNumCol = new TableColumn<TableList, String>(SceneSwitch.getBundle().getString("keyNumQuestion"));
		questionNumCol.setCellValueFactory(new PropertyValueFactory("questionNum"));

		nameCol.setMaxWidth(150);
		authorCol.setMaxWidth(200);
		descriptionCol.setMaxWidth(300);
		questionNumCol.setMaxWidth(200);
		table.getColumns().setAll(nameCol, authorCol, descriptionCol, questionNumCol);
		table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
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
			TimedMessage delay = new TimedMessage();
			_message.setVisible(true);
			//disable the message after a few seconds
			if (!delay.isRunning()){
				delay.start();
			}
			delay.setOnSucceeded(e -> {
	            _message.setVisible(false);
	            delay.reset();
	        });
			
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
			SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
			load.switchScene("/application/view/CustomTestPage.fxml");
		}
	}
	
	@FXML
	public void handlePressAdd(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/CustomCreatePage.fxml");
	}
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
	

	// Event Listener on JFXButton[#_delete].onMouseClicked
	@FXML
	public void handlePressDelete(MouseEvent event) {
		setSelected();
		if (_selected == null) {
			//TODO pop up window;

		}
		else {
			ConfirmationModel confirmation = new ConfirmationModel((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneSwitch.getBundle().getString("keyDeleteQuestionSuite"), SceneSwitch.getBundle().getString("keyDelete"), SceneSwitch.getBundle().getString("keyCancel"));
			boolean confirm = confirmation.createPopUp();
			if (confirm){
				setSelected();
				boolean isPublic = _selected.getPublic();
				System.out.println("isPublic "+isPublic);
				if (isPublic){
					_manager.deleteQuestionSuite(_selected.getName(), isPublic);
					_publicData.remove(_selected);
					//TODO remove selected question suite (Public)
				}
				else {
					_manager.deleteQuestionSuite(_selected.getName(), isPublic);
					_privateData.remove(_selected);
					//TODO remove selected question suite (private)
				}
			}
		}
	}

	public static TableList getSelected() {
		return _selected;
	}
}