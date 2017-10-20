
package application.tableModel;

import javafx.beans.property.SimpleStringProperty;

public class CustomResultModel {

	private SimpleStringProperty question;
	private SimpleStringProperty answer;
	private SimpleStringProperty yourAnswer;
	private SimpleStringProperty score;

	public CustomResultModel(String question, String answer, String yourAnswer, String score) {

		this.question = new SimpleStringProperty(question); 
		this.answer = new SimpleStringProperty(answer);
		this.yourAnswer = new SimpleStringProperty(yourAnswer);
		this.score = new SimpleStringProperty(score);
	}

	public void setQuestion(String value) {
	}
	public String getQuestion() {
		return question.get();
	}
	public void setAnswer(String value) {
	}
	public String getAnswer() {
		return answer.get();
	}
	public void setYourAnswer(String value) {
	}
	public String getYourAnswer(){
		return yourAnswer.get();
	}
	public void setScore(String value) {
	}
	public String getScore(){
		return yourAnswer.get();
	}
}
