package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.viewModel.SceneSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PracticeStatisticPageController {

	private Item[] _list;

	private Map<String, ArrayList<Boolean>> _result = new HashMap<>();

	private int _overallAttempt;

	private int _overallCorrect;

	@FXML
	private Button _h1;

	@FXML
	private Button _h2;

	@FXML
	private Button _h3;

	@FXML
	private Button _h4;

	@FXML
	private Button _h5;

	@FXML
	private Button _h6;

	@FXML
	private Button _h7;

	@FXML
	private Button _h8;

	@FXML
	private Button _l1;

	@FXML
	private Button _l2;

	@FXML
	private Button _l3;

	@FXML
	private Button _l4;

	@FXML
	private Button _l5;

	@FXML
	private Button _l6;
	@FXML
	private Button _l7;
	
	@FXML
	private Button _l8;
	
	@FXML
	private Label _null;

	@FXML
	private PieChart _pie;

	@FXML
	public void initialize() {
		//TODO change to User
		_result = MainPageController.getUser().getOverallStatistic();
		//_result = application.model.PractiseSomething.getResult();
		_list = sortResult();
		setCircles();
		setOverallData();
		setPieChart();
		
		_null.setVisible(false);

	}
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/PracticePage.fxml");
	}

	//sort the numbers according to correct answers
	private Item[] sortResult() {
		List<String> keys = new ArrayList<>(_result.keySet()); 
		List<ArrayList<Boolean>> values = new ArrayList<>(_result.values());

		System.out.println(keys);
		Item[] list = new Item[keys.size()];

		for (int i = 0; i < keys.size(); i++) {
			String num = keys.get(i);
			ArrayList<Boolean> row = values.get(i);
			Item a = new Item(num, row);
			list[i] = a;
		}

		Arrays.sort(list);
		for (Item e: list) {
			System.out.println(e.toString());
		}
		return list;
	}
	private void setCircles() {
		_h1.setText(_list[0]._num);
		_h2.setText(_list[1]._num);
		_h3.setText(_list[2]._num);
		_h4.setText(_list[3]._num);
		_h5.setText(_list[4]._num);
		_h6.setText(_list[5]._num);
		_h7.setText(_list[6]._num);
		_h8.setText(_list[7]._num);
		_l1.setText(_list[_list.length-1]._num);
		_l2.setText(_list[_list.length-2]._num);
		_l3.setText(_list[_list.length-3]._num);
		_l4.setText(_list[_list.length-4]._num);
		_l5.setText(_list[_list.length-5]._num);
		_l6.setText(_list[_list.length-6]._num);
		_l7.setText(_list[_list.length-7]._num);
		_l8.setText(_list[_list.length-8]._num);
	}

	private void setOverallData() {
		_overallCorrect = 0;
		_overallAttempt = 0;
		for (Item e: _list) {
			_overallCorrect = _overallCorrect + e._correct;
			_overallAttempt = _overallAttempt + e._attempt;
		}
	}

	private void setPieChart() {
		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
						new PieChart.Data("Correct", _overallCorrect),
						new PieChart.Data("Incorrect", (_overallAttempt-_overallCorrect)));
		_pie.setData(pieChartData);
		_pie.setLabelsVisible(true);
		_pie.setLegendVisible(true);
	}

	@FXML
	public void enter(MouseEvent event) {
		Item item = null;
		Button select = (Button) event.getSource();
		String text = select.getText();
		//System.out.println(text);
		List<Boolean> row = _result.get(text);
		System.out.println(row);
		int correct = 0;
		int attempt = 0;
		if (row == null) {
			_pie.setVisible(false);
			_null.setVisible(true);
		}
		else {	
			_pie.setVisible(true);
			_null.setVisible(false);
			for (boolean b: row) {
				attempt++;
				if (b == true) {
					correct++;	
				}
			}
		}
		System.out.println(correct);
		System.out.println(attempt-correct);
		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
						new PieChart.Data("Correct", correct),
						new PieChart.Data("Incorrect", attempt-correct));
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
