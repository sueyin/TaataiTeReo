package application.controller;

import java.io.IOException;

import application.model.Question.ClassicQuestion;
import application.model.Question.ClassicQuestionSuite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClassicTestPageController extends TestPageController {

	private static String _selectedLevel;

	private ClassicQuestionSuite _qs;

	private ClassicQuestion _q;
	
	@FXML
	public void initialize() {
		_selectedLevel = ClassicMenuPageController.getSelectedLevel();
		_level.setText("Level "+_selectedLevel);
		_qs = new ClassicQuestionSuite(_selectedLevel);
		//_q = new ClassicQuestion(_qs.getQuestion(), _qs.getAnswer(), this);

		//for now
		_record.setText("(Test) Next");

	}
	
	/**
	 *This method handles event when the button is pressed (button => "record", "next question")
	 */
	@FXML
	public void handlePressButton(MouseEvent event) {
		_question.setText(_qs.getQuestion());
		_qs.nextQuestion();
		//_q.test();
		//_qs.collectResult(_q.getResult());
		_record.setText("Right");
		System.out.println("button pressed");
	}
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicMenuPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	
}
