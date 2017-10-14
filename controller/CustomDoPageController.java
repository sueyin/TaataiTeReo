package application.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CustomDoPageController {

	@FXML
	private TableView _publicList;
	@FXML
	private TableView _privateList;
	@FXML
	private ListView _questionList;
	@FXML
	private JFXButton _do;
	@FXML
	private JFXButton _edit;
	@FXML
	private JFXButton _delete;

	@

	public void initialize() {
		PrivateList a = new PrivateList("a", "b", "c", "d");
		ObservableList<PrivateList> private =new ObservableList<PrivateList>();
		_privateList.setItems( private);

		TableColumn<PrivateList, String> nameCol = new TableColumn<PrivateList, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn<PrivateList, String> authorCol = new TableColumn<PrivateList, String>("Author");
		authorCol.setCellValueFactory(new PropertyValueFactory("author"));
		TableColumn<PrivateList, String> descriptionCol = new TableColumn<PrivateList, String>("Description");
		descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
		TableColumn<PrivateList, String> questionNumCol = new TableColumn<PrivateList, String>("Number of Questions");
		questionNumCol.setCellValueFactory(new PropertyValueFactory("questionNum"));

		_privateList.getColumns().setAll(nameCol, authorCol, descriptionCol, questionNumCol);
	}

	// Event Listener on JFXButton[#_do].onMouseClicked
	@FXML
	public void handlePressDo(MouseEvent event) {

	}

	// Event Listener on JFXButton[#_edit].onMouseClicked
	@FXML
	public void handlePressEdit(MouseEvent event) {

	}

	// Event Listener on JFXButton[#_delete].onMouseClicked
	@FXML
	public void handlePressDelete(MouseEvent event) {

	}
}
public class PrivateList {
	private StringProperty name;
	private StringProperty author;
	private StringProperty description;
	private StringProperty questionNum;
	}

	public PrivateList(String name, String author, String description, String questionNum) {


	}
	public void setName(String value) {
	}
	public String getName(){
		return name.toString();
	}
	public void setAuthor(String value) {
	}
	public String getAuthor() {
		return author.toString();
	}
	public void setDescription(String value) {
	}
	public String getDescription() {
		return description.toString();
	}
	public void setQuestionNum(String value) {
	}
	public String getQuestionNum(){
		return questionNum.toString();
	}
}
