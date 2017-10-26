package application.controller;

import application.model.CustomManager;
import application.model.question.TwoChancesQuestion;
import application.tableModel.TableList;
import application.viewModel.SceneSwitch;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomTestPageController extends TestPageController {

	private static TableList _selectedQS;

	private String _id;

	private boolean _isPublic;

	private CustomManager _manager;

	private Map<Integer, String> _qs = new HashMap<>();

	private ArrayList<Integer> _indexList = new ArrayList<>();

	private static ArrayList<String>  _reportList = new ArrayList<>();

	private int _index;

	/**
	 * Initialize the page
	 */
	@FXML
	public void initialize() {
		//get questions from database
		_reportList.clear();
		_selectedQS = CustomDoPageController.getSelected();
		_isPublic = _selectedQS.getPublic();
		_id = _selectedQS.getName();

		_manager = new CustomManager();
		_qs = _manager.readQuestionSuite(_id, _isPublic);

		//shuffle the questions into random order
		for (Integer i : _qs.keySet()) {
			_indexList.add(i);
		}
		Collections.shuffle(_indexList);
		_index = 0;

		//change the question, answer strings into desired format 
		String custom = _qs.get(_indexList.get(_index));
		String answer = custom.split("#")[0];
		String question = custom.split("#")[1];
		
		//create new question
		_q = new TwoChancesQuestion(question, answer,this);

		//Setup GUI
		_question.setText(_q.getQuestion());
		_record.setVisible(true);
		_next.setVisible(false);
		_play.setVisible(false);
		_process.setVisible(false);
		_loading.setVisible(false);
		_rightOrWrong.setVisible(false);
		_answerIs.setVisible(false);
		_youSaid.setVisible(false);
	}


	/**
	 * When user presses next, switch to the next questions
	 */
	@FXML
	public void handlePressNext(MouseEvent event) {
		//Create report
		String result;
		if(_q.getResult()){
			result = "Right";
		}else{
			result = "Wrong";
		}
		String rep = _q.getQuestion()+"#"+_q.getAnswer()+"#"+_q.getRead()+"#"+ result;
		_reportList.add(rep);

		//if it is the last question, then switch to result page
		if (_index + 1 == _indexList.size()){
			SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
			load.switchScene("/application/view/CustomResultPage.fxml");
		//if it is not the last question, then change GUI to the next question
		}else{
			_index++;
			//get the next question
			nextQuestion();
			_next.setVisible(false);
			_record.setVisible(true);
			//if it is the second to last question, then change the text from "next" to "finish"
			if (_index + 1 == _indexList.size() - 1){
				_next.setText(SceneSwitch.getBundle().getString("keyFinish"));
			}
		}
	}

	/**
	 * This method exists the test page and go back to custom page
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		super.cancelQuestion();
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/CustomDoPage.fxml");
	}


	/**
	 * this method creates questions from the given question suite
	 */
	private void nextQuestion(){
		String custom = _qs.get(_indexList.get(_index));
		String answer = custom.split("#")[0];
		String question = custom.split("#")[1];
		_q = new TwoChancesQuestion(question, answer,this);
		_question.setText(question);
	}


	public static ArrayList<String>  getReport(){
		return _reportList;
	}

}
