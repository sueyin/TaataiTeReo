package application.controller;

import javafx.beans.property.SimpleStringProperty;

public class TableList {
	private SimpleStringProperty name;
	private SimpleStringProperty author;
	private SimpleStringProperty description;
	private SimpleStringProperty questionNum;
	private boolean _public;

	public TableList(String name, String author, String description, String questionNum, boolean isPublic) {
		this.name = new SimpleStringProperty(name);
		this.author = new SimpleStringProperty(author); 
		this.description = new SimpleStringProperty(description);
		this.questionNum = new SimpleStringProperty(questionNum);
		_public = isPublic;

	}
	public void setName(String value) {
	}
	public String getName(){
		return name.get();
	}
	public void setAuthor(String value) {
	}
	public String getAuthor() {
		return author.get();
	}
	public void setDescription(String value) {
	}
	public String getDescription() {
		return description.get();
	}
	public void setQuestionNum(String value) {
	}
	public String getQuestionNum(){
		return questionNum.get();
	}
	public boolean getPublic() {
		return _public;
	}
}
