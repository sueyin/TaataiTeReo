package application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class PracticeStatisticPageController {

	private Item[] _list;

	private Map<String, ArrayList<Boolean>> _result = new HashMap<>();

	private int _overallAttempt;

	private int _overallCorrect;

	@FXML
	private ListView<Item> _top;
	
	@FXML
	private ListView<Item> _bottom;
	
	@FXML
	private Label _null;

	@FXML
	private PieChart _pie;
	
	private ObservableList<Item> _topData; 
	
	private ObservableList<Item> _bottomData; 

	
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
		setPieChart();
		//set twos tables to show the most well done 8 and most poorly done 8 numbers 
		setTable();
		//initialize the "no attempt" message to false
		_null.setVisible(false);

	}
	
	/**
	 * return to practice page when user press return
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticePage.fxml");
	}

	/**
	 * sort the numbers according to correct answers, returns a sorted array of items
	 */
	private Item[] sortResult() {
		//get the numbers 1-99 and their corresponding result history
		List<String> keys = new ArrayList<>(_result.keySet()); 
		List<ArrayList<Boolean>> values = new ArrayList<>(_result.values());

		//create an array of items based on the data from previous lines 
		Item[] list = new Item[keys.size()];

		for (int i = 0; i < keys.size(); i++) {
			String num = keys.get(i);
			ArrayList<Boolean> row = values.get(i);
			Item a = new Item(num, row);
			list[i] = a;
		}

		//sort the array 
		Arrays.sort(list);
		for (Item e: list) {
			System.out.println(e.toString());
		}
		return list;
	}

	/**
	 * calculate overall correctness and attempts done by the user
	 */
	private void setOverallData() {
		_overallCorrect = 0;
		_overallAttempt = 0;
		for (Item e: _list) {
			_overallCorrect = _overallCorrect + e._correct;
			_overallAttempt = _overallAttempt + e._attempt;
		}
	}

	/**
	 * set pie chart value to overall data statistic
	 */
	private void setPieChart() {
		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
						new PieChart.Data("Correct", _overallCorrect),
						new PieChart.Data("Incorrect", (_overallAttempt-_overallCorrect)));
		_pie.setData(pieChartData);
		_pie.setLabelsVisible(true);
		_pie.setLegendVisible(true);
	}

	/**
	 * set the table to show 8 most well done and 8 worst done numbers
	 */
	private void setTable() {
		//get the 8 most well done numbers from the sorted array
		List<Item> topData = new ArrayList<Item>(8);
		for (int i = 0; i < 8; i++) {
			topData.add(_list[i]);
		}
		//set up the list view
		_topData = FXCollections.observableArrayList (topData);
		_top.setItems(_topData);
		
		//call the changeInfo method every time user selects something from the list
		_top.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
		    @Override
		    public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
		        changeInfo(_top);
		    }
		});
		//get the 8 worst numbers from the sorted array
		List<Item> bottomData = new ArrayList<Item>(8);
		for (int i = 0; i < 8; i++) {
			topData.add(_list[_list.length-i]);
		}
		//set up the list view
		_bottomData = FXCollections.observableArrayList (bottomData);
		_bottom.setItems(_bottomData);
		
		//call the changeInfo method every time user selects something from the list
		_bottom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
		    @Override
		    public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
		    	changeInfo(_bottom);
		    }
		});
	}
	
	/**
	 * update the pie chart to show result of the number that user has selected from the list
	 */
	private void changeInfo(ListView<Item> list) {
		Item selected = (Item) list.getSelectionModel().getSelectedItem();
		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
						new PieChart.Data("Correct", selected._correct),
						new PieChart.Data("Incorrect", selected._attempt-selected._correct));
		_pie.setData(pieChartData);
		_pie.setLabelsVisible(true);
		_pie.setLegendVisible(true);
	}
}



class Item implements Comparable<Item>{
	String _num;
	ArrayList<Boolean> _data;
	int _correct;
	int _attempt;

	public Item(String num, ArrayList<Boolean> data) {
		_num = num;
		_data = data;
	}

	public double getCorrectPercent() {
		_attempt = 0;
		if (_data == null) {
			_correct = 0;
			return 0;
		}
		else {
			int count = 0;
			for (boolean b : _data) {
				_attempt++;
				if (b == true) {
					count++;
				}
				else{
				}
			}
			_correct = count;
			return ((double) count/_attempt);
		}
	}

	public String toString() {
		String output = "" + _num + " " + this.getCorrectPercent();
		return output;
	}

	public String getName() {
		return _num;
	}
	
	@Override
	public int compareTo(Item a) {
		double num1 = this.getCorrectPercent();
		double num2 = a.getCorrectPercent();

		return Double.compare(num2, num1);
	}
}
