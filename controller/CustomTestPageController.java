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

	private Map<String, String> _qs = new HashMap<>();

	private ArrayList<String> _answerList = new ArrayList<>();

	private int _index;



	@FXML
	public void initialize() {
		_selectedQS = CustomDoPageController.getSelected();
		_isPublic = _selectedQS.getPublic();
		_id = _selectedQS.getName();

		_manager = new CustomManager();
		_qs = _manager.readQuestionSuite(_id, _isPublic);
		for (String s : _qs.keySet()) {
			_answerList.add(s);
		}
		Collections.shuffle(_answerList);
		_index = 0;
	}


	// Event Listener on JFXButton[#_next].onMouseClicked
	@FXML
	public void handlePressNext(MouseEvent event) {
		if (_index + 1 == _answerList.size()){


			
		}else{
			_index++;
		}
	}

	// Event Listener on JFXButton[#_return].onMouseClicked
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/CustomDoPage.fxml");
	}

	@Override
	public void collectResult() {
		if (_q.getResult()){
			_score++;
		}
	}
}
