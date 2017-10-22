package application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXButton;

import application.tableModel.PracticeResultModel;
import application.tableModel.PracticeResultModel;
import application.viewModel.SceneSwitch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class PracticeStatisticPageController {

	private PracticeResultModel[] _list;

	private Map<String, ArrayList<Boolean>> _result = new HashMap<>();

	private int _overallAttempt;

	private int _overallCorrect;

	@FXML
	private TableView<PracticeResultModel> _top;
	
	@FXML
	private TableView<PracticeResultModel> _bottom;
	
	@FXML
	private Label _null;

	@FXML
	private PieChart _pie;
	
	@FXML
	private JFXButton _overall;
	
	@FXML
	private Label _selected;
	
	private ObservableList<PracticeResultModel> _topData; 
	
	private ObservableList<PracticeResultModel> _bottomData; 

	
	@FXML
	public void initialize() {
		//TODO change to User
		_result = MainPageController.getUser().getOverallStatistic();
		//_result = application.model.PractiseSomething.getResult();
		//sort numbers 1-99 in order of correctness from user's result history
		_list = sortResult();
		//calculate overall number of correctness and attempts
		setOverallData();
		//set pie chart to present the overall result
		setPieOverall();
		//set twos tables to show the most well done 8 and most poorly done 8 numbers 
		setTable();
		//initialize the "no attempt" message to false
		_null.setVisible(false);
		_overall.setText(SceneSwitch.getBundle().getString("keyOverallCorrect")+" "+_overallCorrect+SceneSwitch.getBundle().getString("keyOverallAttempts")+ ""+_overallAttempt);
		_selected.setText(SceneSwitch.getBundle().getString("keyOverallData"));

	}
	
	/**
	 * return to practice page when user press return
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticePage.fxml");
	}

	@FXML
	public void handlePressOverall(MouseEvent event) {
		_selected.setText(SceneSwitch.getBundle().getString("keyOverallData"));
		if (_overallAttempt == 0) {
			_null.setVisible(true);
			_pie.setVisible(false);
		}
		else {
			_null.setVisible(false);
			_pie.setVisible(true);
			setPieOverall();
		}
	}
	/**
	 * sort the numbers according to correct answers, returns a sorted array of items
	 */
	private PracticeResultModel[] sortResult() {
		//get the numbers 1-99 and their corresponding result history
		List<String> keys = new ArrayList<>(_result.keySet()); 
		List<ArrayList<Boolean>> values = new ArrayList<>(_result.values());

		//create an array of items based on the data from previous lines 
		PracticeResultModel[] list = new PracticeResultModel[keys.size()];

		for (int i = 0; i < keys.size(); i++) {
			String num = keys.get(i);
			ArrayList<Boolean> row = values.get(i);
			PracticeResultModel a = new PracticeResultModel(num, row);
			list[i] = a;
		}

		//sort the array 
		Arrays.sort(list);
		return list;
	}

	/**
	 * calculate overall correctness and attempts done by the user
	 */
	private void setOverallData() {
		_overallCorrect = 0;
		_overallAttempt = 0;
		for (PracticeResultModel e: _list) {
			_overallCorrect = _overallCorrect + e.getCorrect();
			_overallAttempt = _overallAttempt + e.getAttempt();
		}
	}

	/**
	 * set pie chart value to overall data statistic
	 */
	private void setPieOverall() {
		setPie(_overallCorrect, _overallAttempt);
	}
	
	/**
	 * set the table to show 8 most well done and 8 worst done numbers
	 */
	private void setTable() {
		//get the 8 most well done numbers from the sorted array
		List<PracticeResultModel> topData = new ArrayList<PracticeResultModel>(5);
		for (int i = 0; i < 5; i++) {
			topData.add(_list[i]);
		}
		//set up the list view
		_topData = FXCollections.observableArrayList (topData);
		_top.setItems(_topData);
		setTableList(_top);

		//get the 8 worst numbers from the sorted array
		List<PracticeResultModel> bottomData = new ArrayList<PracticeResultModel>(5);
		for (int i = 0; i < 5; i++) {
			bottomData.add(_list[_list.length-i-1]);
		}
		//set up the list view
		_bottomData = FXCollections.observableArrayList (bottomData);
		_bottom.setItems(_bottomData);
		
		setTableList(_bottom);
	}
	
	private void setPie(int correctNum, int totalNum){
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		PieChart.Data correct = new PieChart.Data(SceneSwitch.getBundle().getString("keyCorrect"), correctNum);
		PieChart.Data total = new PieChart.Data(SceneSwitch.getBundle().getString("keyIncorrect"), totalNum);		
		pieChartData.addAll(correct, total);
		_pie.setData(pieChartData);
		_pie.setLabelsVisible(true);
		_pie.setLegendVisible(false);
		correct.getNode().setStyle("-fx-pie-color: #628462;");		
		total.getNode().setStyle("-fx-pie-color: #555e55;");
	}
	
	/**
	 * Initialize the table view passed in as parameters, creates three columns: Number, Correct Attempts, and total attempts
	 */
	private void setTableList(TableView<PracticeResultModel> table) {
		TableColumn<PracticeResultModel, String> numCol = new TableColumn<PracticeResultModel, String>(SceneSwitch.getBundle().getString("keyNumber"));
		numCol.setCellValueFactory(new PropertyValueFactory("num"));
		TableColumn<PracticeResultModel, Integer> correctCol = new TableColumn<PracticeResultModel, Integer>(SceneSwitch.getBundle().getString("keyCorrectAttempts"));
		correctCol.setCellValueFactory(new PropertyValueFactory("correct"));
		TableColumn<PracticeResultModel, Integer> attemptCol = new TableColumn<PracticeResultModel, Integer>(SceneSwitch.getBundle().getString("keyIncorrectAttempts"));
		attemptCol.setCellValueFactory(new PropertyValueFactory("attempt"));


		table.getColumns().setAll(numCol, correctCol, attemptCol);
		table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
		
		//call the changeInfo method every time user selects something from the list
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PracticeResultModel>() {
		    @Override
		    public void changed(ObservableValue<? extends PracticeResultModel> observable, PracticeResultModel oldValue, PracticeResultModel newValue) {
		    	changeInfo(table);
		    }
		});
	}
	
	/**
	 * update the pie chart to show result of the number that user has selected from the list
	 */
	private void changeInfo(TableView<PracticeResultModel> list) {
		PracticeResultModel selected = (PracticeResultModel) list.getSelectionModel().getSelectedItem();
		_selected.setText(SceneSwitch.getBundle().getString("keyResultsFor")+ selected.getName());
		if (selected.getAttempt().intValue()==0) {
			_null.setVisible(true);
			_pie.setVisible(false);
		}
		else {
			_null.setVisible(false);
			_pie.setVisible(true);
			setPie(selected.getCorrect(), selected.getAttempt()-selected.getCorrect());
		}

	}
}
