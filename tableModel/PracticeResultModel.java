package application.tablemodel;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;


public class PracticeResultModel implements Comparable<PracticeResultModel>{
	private SimpleStringProperty num;
	ArrayList<Boolean> _data;
	int correct;
	int attempt;

	public void setNum(String value) {
	}
	public String getNum(){
		return num.get();
	}
	public void setCorrect(int value) {
	}
	public Integer getCorrect() {
		return correct;
	}
	public void setAttempt(int value) {
	}
	public Integer getAttempt() {
		return attempt;
	}
	

	public PracticeResultModel(String num, ArrayList<Boolean> data) {
		this.num = new SimpleStringProperty(num);
		_data = data;
	}

	public double getCorrectPercent() {
		attempt = 0;
		if (_data == null) {
			correct = 0;
			return 0;
		}
		else {
			int count = 0;
			for (boolean b : _data) {
				attempt++;
				if (b == true) {
					count++;
				}
				else{
				}
			}
			correct = count;
			return ((double) count/attempt);
		}
	}

	public String toString() {
		String output = num.get();
		return output;
	}

	public String getName() {
		return num.get();
	}

	@Override
	public int compareTo(PracticeResultModel a) {
		double num1 = this.getCorrectPercent();
		double num2 = a.getCorrectPercent();

		return Double.compare(num2, num1);
	}
}
