package application.tablemodel;

import javafx.beans.property.SimpleStringProperty;

public class StarModel {
	private SimpleStringProperty user;
	private SimpleStringProperty star;

	public StarModel(String user, String star) {
		this.user = new SimpleStringProperty(user);
		this.star = new SimpleStringProperty(star); 

	}
	public void setUser(String value) {
	}
	public String getUser(){
		return user.get();
	}
	public void setStar(String value) {
	}
	public String getStar() {
		return star.get();
	}
}
