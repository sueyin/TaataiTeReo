package application.tableModel;

import javafx.beans.property.SimpleStringProperty;

public class SurvivalModel {
	private SimpleStringProperty user;
	private SimpleStringProperty survival;

	public SurvivalModel(String user, String survival) {
		this.user = new SimpleStringProperty(user);
		this.survival = new SimpleStringProperty(survival); 

	}
	public void setUser(String value) {
	}
	public String getUser(){
		return user.get();
	}
	public void setSurvival(String value) {
	}
	public String getSurvival() {
		return survival.get();
	}
}
