package application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import application.tableModel.ExpModel;
import application.tableModel.StarModel;
import application.tableModel.SurvivalModel;
import application.viewModel.SceneSwitch;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomLeaderBoardPageController {
	@FXML
	private TableView<ExpModel> _expTable;
	@FXML
	private TableView<StarModel> _starTable;
	@FXML
	private TableView<SurvivalModel> _survivalTable;
	@FXML
	private JFXButton _return;
	
	private ObservableList<ExpModel> _expData= FXCollections.observableArrayList();
	private ObservableList<StarModel> _starData= FXCollections.observableArrayList();	
	private ObservableList<SurvivalModel> _survivalData= FXCollections.observableArrayList();
	@FXML
	public void initialize() {
		setUpExpTable();
		setUpStarTable();
		setUpSurvivalTable();
	}
	
	private void setUpExpTable(){
		_expData = FXCollections.observableArrayList();
		ExpModel a = new ExpModel("sueyin", "383");
		_expData.add(a);
		_expTable.setItems(_expData);
		_expTable.setEditable(true);
		
		TableColumn<ExpModel, String> userCol = new TableColumn<ExpModel, String>("User");
		userCol.setCellValueFactory(new PropertyValueFactory<ExpModel, String>("user"));
		TableColumn<ExpModel, String> expCol = new TableColumn<ExpModel, String>("EXP");
		expCol.setCellValueFactory(new PropertyValueFactory<ExpModel, String>("exp"));
		
		_expTable.getColumns().setAll(userCol, expCol);
		_expTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	private void setUpStarTable() {
		_starData = FXCollections.observableArrayList();
		StarModel a = new StarModel("sueyin", "654");
		_starData.add(a);
		_starTable.setItems(_starData);
		_starTable.setEditable(true);
		
		TableColumn<StarModel, String> userCol = new TableColumn<StarModel, String>("User");
		userCol.setCellValueFactory(new PropertyValueFactory<StarModel, String>("user"));
		TableColumn<StarModel, String> starCol = new TableColumn<StarModel, String>("Star");
		starCol.setCellValueFactory(new PropertyValueFactory<StarModel, String>("star"));
		
		_starTable.getColumns().setAll(userCol, starCol);
		_starTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	private void setUpSurvivalTable() {
		_survivalData = FXCollections.observableArrayList();
		SurvivalModel a = new SurvivalModel("sueyin", "89");
		_survivalData.add(a);
		_survivalTable.setItems(_survivalData);
		_survivalTable.setEditable(true);
		
		TableColumn<SurvivalModel, String> userCol = new TableColumn<SurvivalModel, String>("User");
		userCol.setCellValueFactory(new PropertyValueFactory<SurvivalModel, String>("user"));
		TableColumn<SurvivalModel, String> survivalCol = new TableColumn<SurvivalModel, String>("Survival");
		survivalCol.setCellValueFactory(new PropertyValueFactory<SurvivalModel, String>("survival"));
		
		_survivalTable.getColumns().setAll(userCol, survivalCol);
		_survivalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/CustomDoPage.fxml");
	}

}
