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

public class CustomResultPageController {

	@FXML
	private TableView _tableView;
	
	@FXML
	private JFXButton _return;
	
	private ObservableList<CustomResultModel> _data= FXCollections.observableArrayList();
	
	
	@FXML
	public void initialize() {
		_data = FXCollections.observableArrayList();
		CustomResultModel a = new CustomResultModel("question","answer","yourAnswer","score");
		_data.add(a);
		_tableView.setItems(_data);
		_tableView.setEditable(true);
		

		TableColumn<CustomResultModel, String> questionCol = new TableColumn<CustomResultModel, String>("Question");
		questionCol.setCellValueFactory(new PropertyValueFactory<CustomResultModel, String>("question"));
		TableColumn<CustomResultModel, String> answerCol = new TableColumn<CustomResultModel, String>("Answer");
		answerCol.setCellValueFactory(new PropertyValueFactory<CustomResultModel, String>("answer"));
		TableColumn<CustomResultModel, String> yourAnswerCol = new TableColumn<CustomResultModel, String>("Your Answer");
		yourAnswerCol.setCellValueFactory(new PropertyValueFactory<CustomResultModel, String>("yourAnswer"));
		TableColumn<CustomResultModel, String> scoreCol = new TableColumn<CustomResultModel, String>("Score");
		scoreCol.setCellValueFactory(new PropertyValueFactory<CustomResultModel, String>("score"));

		_tableView.getColumns().setAll(questionCol, answerCol, yourAnswerCol, scoreCol);
		_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/CustomDoPage.fxml");
	}
}