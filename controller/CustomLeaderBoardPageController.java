package application.controller;

import application.TataiApp;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.File;
import java.util.*;

import com.jfoenix.controls.JFXButton;

import application.tableModel.ExpModel;
import application.tableModel.StarModel;
import application.tableModel.SurvivalModel;
import application.viewModel.SceneSwitch;
import javafx.scene.Node;
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

	//Functionality
	private List<User> _usrs = new ArrayList<>();

	private Map<String, Integer> _expRank = new HashMap<>();
	private Map<String, Integer> _starRank = new HashMap<>();
	private Map<String, Integer> _survivalRank = new HashMap<>();

	private ObservableList<ExpModel> _expData= FXCollections.observableArrayList();
	private ObservableList<StarModel> _starData= FXCollections.observableArrayList();	
	private ObservableList<SurvivalModel> _survivalData= FXCollections.observableArrayList();


	@FXML
	public void initialize() {
		readUsers();
		for (User u : _usrs){
			_expRank.put(u.getName(), u.getExp());
			_starRank.put(u.getName(), u.getStars());
			_survivalRank.put(u.getName(), u.getSurvivalScore());
		}
		_expRank = sortByRecord(_expRank);
		_starRank = sortByRecord(_starRank);
		_survivalRank = sortByRecord(_survivalRank);
		setUpExpTable();
		setUpStarTable();
		setUpSurvivalTable();
	}
	
	private void setUpExpTable(){
		_expData = FXCollections.observableArrayList();
		for (Map.Entry<String, Integer> entry : _expRank.entrySet())
		{
			ExpModel a = new ExpModel(entry.getKey(), Integer.toString(entry.getValue()));
			_expData.add(a);
		}
		_expTable.setItems(_expData);
		_expTable.setEditable(true);
		
		TableColumn<ExpModel, String> userCol = new TableColumn<ExpModel, String>(SceneSwitch.getBundle().getString("keyUser"));
		userCol.setCellValueFactory(new PropertyValueFactory<ExpModel, String>("user"));
		TableColumn<ExpModel, String> expCol = new TableColumn<ExpModel, String>("EXP");
		expCol.setCellValueFactory(new PropertyValueFactory<ExpModel, String>("exp"));
		
		_expTable.getColumns().setAll(userCol, expCol);
		_expTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	private void setUpStarTable() {
		_starData = FXCollections.observableArrayList();
		for (Map.Entry<String, Integer> entry : _starRank.entrySet())
		{
			StarModel a = new StarModel(entry.getKey(), Integer.toString(entry.getValue()));
			_starData.add(a);
		}
		_starTable.setItems(_starData);
		_starTable.setEditable(true);
		
		TableColumn<StarModel, String> userCol = new TableColumn<StarModel, String>(SceneSwitch.getBundle().getString("keyUser"));
		userCol.setCellValueFactory(new PropertyValueFactory<StarModel, String>("user"));
		TableColumn<StarModel, String> starCol = new TableColumn<StarModel, String>("Star");
		starCol.setCellValueFactory(new PropertyValueFactory<StarModel, String>("star"));
		
		_starTable.getColumns().setAll(userCol, starCol);
		_starTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	private void setUpSurvivalTable() {
		_survivalData = FXCollections.observableArrayList();
		for (Map.Entry<String, Integer> entry : _survivalRank.entrySet())
		{
			SurvivalModel a = new SurvivalModel(entry.getKey(), Integer.toString(entry.getValue()));
			_survivalData.add(a);
		}
		_survivalTable.setItems(_survivalData);
		_survivalTable.setEditable(true);
		
		TableColumn<SurvivalModel, String> userCol = new TableColumn<SurvivalModel, String>(SceneSwitch.getBundle().getString("keyUser"));
		userCol.setCellValueFactory(new PropertyValueFactory<SurvivalModel, String>("user"));
		TableColumn<SurvivalModel, String> survivalCol = new TableColumn<SurvivalModel, String>(SceneSwitch.getBundle().getString("keySurvival"));
		survivalCol.setCellValueFactory(new PropertyValueFactory<SurvivalModel, String>("survival"));
		
		_survivalTable.getColumns().setAll(userCol, survivalCol);
		_survivalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}


	private void readUsers(){
		File urs = new File(TataiApp.getUserDir());
		for (File f : urs.listFiles()){
			_usrs.add(new User(f.getName()));
		}
	}

	private Map<String, Integer> sortByRecord(Map<String, Integer> unsortMap)
	{
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
		// Sorting the list based on values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}
