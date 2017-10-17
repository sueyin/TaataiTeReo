package application.tableModel;

import javafx.beans.property.SimpleStringProperty;

public class ExpModel {
	private SimpleStringProperty user;
	private SimpleStringProperty exp;

	public ExpModel(String user, String exp) {
		this.user = new SimpleStringProperty(user);
		this.exp = new SimpleStringProperty(exp); 

	}
	public void setUser(String value) {
	}
	public String getUser(){
		return user.get();
	}
	public void setExp(String value) {
	}
	public String getExp() {
		return exp.get();
	}
}
