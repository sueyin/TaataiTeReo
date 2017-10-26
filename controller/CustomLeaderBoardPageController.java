package application.controller;

import application.TataiApp;
import application.model.admin.User;
import application.tablemodel.ExpModel;
import application.tablemodel.StarModel;
import application.tablemodel.SurvivalModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.File;
import java.util.*;

import com.jfoenix.controls.JFXButton;

import application.viewmodel.SceneSwitch;
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


	/**
	 * This method initialize the page and load any data necessary
	 */
	@FXML
	public void initialize() {
		//read in all users
		readUsers();
		//get the exp, stars, and high score data for each user
		for (User u : _usrs){
			_expRank.put(u.getName(), u.getExp());
			_starRank.put(u.getName(), u.getStars());
			_survivalRank.put(u.getName(), u.getSurvivalScore());
		}
		//sort the users by the three factors
		_expRank = sortByRecord(_expRank);
		_starRank = sortByRecord(_starRank);
		_survivalRank = sortByRecord(_survivalRank);
		//set up the three tables for each of the three factors
		setUpExpTable();
		setUpStarTable();
		setUpSurvivalTable();
	}
	
	/**
	 * This method sets up the Exp table
	 */
	private void setUpExpTable(){
		_expData = FXCollections.observableArrayList();
		//load in the exp value of every users in sorted order
		
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
	
	/**
	 * This method sets up the star table
	 */
	private void setUpStarTable() {
		_starData = FXCollections.observableArrayList();
		//load in the number of stars of every users earned in sorted order
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
	
	/**
	 * this method sets up the survivial high score table
	 */
	private void setUpSurvivalTable() {
		_survivalData = FXCollections.observableArrayList();
		//load in the high score of every users in sorted order
		for (Map.Entry<String, Integer> entry : _survivalRank.entrySet())
		{
			SurvivalModel a = new SurvivalModel(entry.getKey(), Integer.toString(entry.getValue()));
			_survivalData.add(a);
		}
		_survivalTable.setItems(_survivalData);
		_survivalTable.setEditable(true);
		
		//create columns
		TableColumn<SurvivalModel, String> userCol = new TableColumn<SurvivalModel, String>(SceneSwitch.getBundle().getString("keyUser"));
		userCol.setCellValueFactory(new PropertyValueFactory<SurvivalModel, String>("user"));
		TableColumn<SurvivalModel, String> survivalCol = new TableColumn<SurvivalModel, String>(SceneSwitch.getBundle().getString("keySurvival"));
		survivalCol.setCellValueFactory(new PropertyValueFactory<SurvivalModel, String>("survival"));
		
		_survivalTable.getColumns().setAll(userCol, survivalCol);
		_survivalTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	/**
	 * This method switches from leader board page to main page
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}

	/**
	 * This method reads all the user information from data base
	 */
	private void readUsers(){
		File urs = new File(TataiApp.getUserDir());
		for (File f : urs.listFiles()){
			_usrs.add(new User(f.getName()));
		}
	}

	/**
	 * Sort the users base on their high score, number of stars and exp value
	 */
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
		int i = 0;
		for (Map.Entry<String, Integer> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
			i++;
			if (i == 14){
				break;
			}
		}
		return sortedMap;
	}

}
