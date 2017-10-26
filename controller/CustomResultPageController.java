package application.controller;

import com.jfoenix.controls.JFXButton;

import application.tableModel.CustomResultModel;
import application.viewModel.SceneSwitch;
import application.tableModel.CustomResultModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomResultPageController {

	@FXML
	private TableView _tableView;
	
	@FXML
	private JFXButton _return;
	
	private ObservableList<CustomResultModel> _data= FXCollections.observableArrayList();
	
	
	/**
	 * initalize the page, and create the table to display details on the questions done
	 */
	@FXML
	public void initialize() {
		_data = FXCollections.observableArrayList();
		//collect information on the result of the questions suite done
		ArrayList<String> report = CustomTestPageController.getReport();
		
		//add data into the table
		for (String s : report) {
			String[] individualRep = s.split("#");
			CustomResultModel a = new CustomResultModel(individualRep[0], individualRep[1], individualRep[2], individualRep[3]);
			_data.add(a);
			_tableView.setItems(_data);
			_tableView.setEditable(true);
		}

		//create columns: question, answer, yourAnswer, and score in the table
		TableColumn<CustomResultModel, String> questionCol = new TableColumn<CustomResultModel, String>(SceneSwitch.getBundle().getString("keyQuestion"));
		questionCol.setCellValueFactory(new PropertyValueFactory<CustomResultModel, String>("question"));
		TableColumn<CustomResultModel, String> answerCol = new TableColumn<CustomResultModel, String>(SceneSwitch.getBundle().getString("keyAnswer"));
		answerCol.setCellValueFactory(new PropertyValueFactory<CustomResultModel, String>("answer"));
		TableColumn<CustomResultModel, String> yourAnswerCol = new TableColumn<CustomResultModel, String>(SceneSwitch.getBundle().getString("keyRightAnswer"));
		yourAnswerCol.setCellValueFactory(new PropertyValueFactory<CustomResultModel, String>("yourAnswer"));
		TableColumn<CustomResultModel, String> scoreCol = new TableColumn<CustomResultModel, String>(SceneSwitch.getBundle().getString("keyResult"));
		scoreCol.setCellValueFactory(new PropertyValueFactory<CustomResultModel, String>("score"));

		_tableView.getColumns().setAll(questionCol, answerCol, yourAnswerCol, scoreCol);
		_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	/**
	 * this method switches to custom page when user decides to exit
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/CustomDoPage.fxml");
	}
}