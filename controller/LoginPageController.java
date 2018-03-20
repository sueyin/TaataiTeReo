package application.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import application.TataiApp;
import application.confirmation.ConfirmationModel;
import application.model.admin.User;
import application.viewmodel.SceneSwitch;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginPageController {

	@FXML
	private Stage _deleteConfirm;

	@FXML
	private JFXListView<String> _userList; // the list displayed on select user page with all existing user names

	@FXML
	private Button _add;  //add user button

	@FXML
	private Button _delete;  //delete user button

	@FXML
	private Button _selectUserContinue;  //continue to main page button from the select user page 

	@FXML
	private Label _selectUserMessage;  // warning message displayed on select user page if the user press continue without selecting a user 

	@FXML
	private JFXTextField _textField;  //text field on add user page for entering new user name 

	@FXML
	private Button _addUserContinue; //continue to main page button from the add user page 

	@FXML
	private Label _addUserMessage;// warning message displayed on add user page if the user press continue without entering a name or the name already exists  

	@FXML
	private Button _return; //return button that switch from add user page to select user page 

	@FXML
	private Label _languageLabel;
	
	@FXML
	private Button _chinese;
	
	@FXML
	private Button _english;

	private List<String> _data = new ArrayList<String>(); //data extracted from txt file............................

	private ObservableList<String> _items; 

	private boolean _toDelete;

	private static String _selectedUser; //the user that is going to be used in the game

	ObservableList<String> options = FXCollections.observableArrayList("English","中文");


	/**
	 * initialize the select user page
	 */
	@FXML
	public void initialize() {
		//show the select user page first
		showSelectUserPage(true);

		//initialize list
		initialiseUserList();

		//initially set the delete button to be invisible until a user is selected in the list
		_delete.setVisible(false);

		//initially set the warning message to invisible, only displayed when user attempt to continue without select user
		_selectUserMessage.setVisible(false);
	}

	/**
	 * Switch to Chinese language 
	 */
	@FXML
	public void handlePressChinese(MouseEvent event) {
		SceneSwitch.setLanguage("中文");
		SceneSwitch load = new SceneSwitch((Stage) _languageLabel.getScene().getWindow());
		load.switchScene("/application/view/LoginPage.fxml");
	}
	
	/**
	 * Switch to English language
	 */
	@FXML
	public void handlePressEnglish(MouseEvent event) {
		SceneSwitch.setLanguage("English");
		SceneSwitch load = new SceneSwitch((Stage) _languageLabel.getScene().getWindow());
		load.switchScene("/application/view/LoginPage.fxml");
	}
	
	
	/**
	 * This method changes from select user page to add user page
	 */
	@FXML
	public void handlePressAddUser(MouseEvent event) {
		//initialize the text field for entering use name and warning message to empty 
		_addUserMessage.setText("");
		_textField.setText("");

		//switch to add user page
		showSelectUserPage(false);

		_textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER)  {
					performAfterPressEnter();
				}
			}
		});
	}


	/**
	 * This method deletes the selected user when delete button is pressed
	 */
	@FXML
	public void handlePressDeleteUser(MouseEvent event) {
		//opens a window that confirms if the user want to delete the user
		ConfirmationModel confirmation = new ConfirmationModel((Stage) ((Node) event.getSource()).getScene().getWindow(), SceneSwitch.getBundle().getString("keyDeleteUser"), SceneSwitch.getBundle().getString("keyDelete"),SceneSwitch.getBundle().getString("keyCancel"));
		boolean confirm = confirmation.createPopUp();
		if (confirm){
			//Delete the User records
			String toDelete = _userList.getSelectionModel().getSelectedItem();
			new User(toDelete).deleteUser();
			//Delete private custom questions
			File custom = new File(TataiApp.getCustomDir() + toDelete);
			if (custom.exists()) {
				if (custom.listFiles() != null) {
					for (File f : custom.listFiles()) {
						f.delete();
					}
				}
				custom.delete();
			}
			_items.remove(toDelete);
			_data.remove(toDelete);
		}
	}




	/**
	 * This method switches from select user page to main page when user presses continue button and a user if selected
	 */
	@FXML
	public void handlePressContinueFromSelectUser(MouseEvent event) {

		//get current selected user from the list
		_selectedUser = _userList.getSelectionModel().getSelectedItem();

		//check if user selected a user, if not, then shows warning message
		if (_selectedUser == null) {
			TimedMessage delay = new TimedMessage();
			_selectUserMessage.setVisible(true);
			//disable the message after a few seconds
			if (!delay.isRunning()){
				delay.start();
			}
			delay.setOnSucceeded(e -> {
				_selectUserMessage.setVisible(false);
				delay.reset();
			});
		}
		//if a user is selected, then save the choice and continue to main page
		else {
			saveNewUserName(_selectedUser);
			SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
			load.switchScene("/application/view/MainPage.fxml");
		}
	}

	/**
	 * This method changes from add user page to main page if the entered user name is valid 
	 */
	@FXML
	public void handlePressContinueFromAddUser(MouseEvent event) {
		performAfterPressEnter();
	}



	/**
	 * This method changes from the add user page to select user page when the return button is pressed
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		showSelectUserPage(true);
		

		//when switch back to select user page, if nothing is selected from the list, then disable delete user button
		if (_userList.getSelectionModel().getSelectedItem() == null) {
			_delete.setVisible(false);
		}
	}

	/**
	 * This methods takes in boolean parameter, and displays the select user page when true, displays the add user page when fslse.
	 */
	private void showSelectUserPage(boolean show) {
		_add.setVisible(show);
		_delete.setVisible(show);
		_userList.setVisible(show);
		_selectUserContinue.setVisible(show);
		_return.setVisible(!show);
		_textField.setVisible(!show);
		_addUserContinue.setVisible(!show);
		_addUserMessage.setVisible(!show);
		_chinese.setVisible(show);
		_english.setVisible(show);
		_languageLabel.setVisible(show);
	}

	/**
	 * This method initializes the user list when launching this page. It gets data on existing users from txt file
	 */
	private void initialiseUserList(){
		_data.clear();
		File usrDir = new File(TataiApp.getUserDir());
		for (String user : Arrays.asList(usrDir.list())){
			_data.add(user);
		}

		_items =FXCollections.observableArrayList (_data);
		_userList.setItems(_items); 

		//Enables the delete button when an item is selected from the user list
		_userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				_delete.setVisible(true);
			}
		});
	}


	/**
	 * saves a user name to the list and also file containing all the user name
	 */
	private void saveNewUserName(String username) {
		_items.add(username);
		File newUser = new File (TataiApp.getUserDir() + username);
		if (!newUser.exists()){
			newUser.mkdir();
		}
	}

	public static String getSelectedUser() {

		return _selectedUser;
	}

	private void performAfterPressEnter() {
		Service delay = new TimedMessage();

		//check if the entered name exist already 
		_selectedUser = _textField.getText();
		boolean repeated = false;
		for (String existedUser: _data) {
			if (_selectedUser.equals(existedUser)){
				repeated = true;
			}
		}
		//check if the text field is empty, if so then display warning message
		if (_selectedUser.equals("")) {
			_addUserMessage.setText(SceneSwitch.getBundle().getString("keyEnterUsername"));
			_addUserMessage.setVisible(true);
			//disable the warning message after a few seconds
			if (!delay.isRunning()){
				delay.start();
			}
			delay.setOnSucceeded(e -> {
				_addUserMessage.setVisible(false);
				delay.reset();
			});
		}
		//check if the text field only contains numbers and letters, if not then display warning message
		else if (!_selectedUser.matches("\\w+")) {
			_addUserMessage.setText(SceneSwitch.getBundle().getString("keyUsernameType"));
			_addUserMessage.setVisible(true);
			if (!delay.isRunning()){
				delay.start();
			}
			delay.setOnSucceeded(e -> {
				_addUserMessage.setVisible(false);
				delay.reset();
			});
		}
		//check if the entered user name already exists, if so, then asks the user to enter another one
		else if (repeated == true) {
			_addUserMessage.setText(SceneSwitch.getBundle().getString("keyUsernameRepeat"));
			_addUserMessage.setVisible(true);
			//disable the warning message after a few seconds
			if (!delay.isRunning()){
				delay.start();
			}
			delay.setOnSucceeded(e -> {
				_addUserMessage.setVisible(false);
				delay.reset();
			});
		}
		//if the user name entered is valid, then proceed to next page and save user name
		else {
			saveNewUserName(_selectedUser);
			SceneSwitch load = new SceneSwitch((Stage) ((Node) _textField).getScene().getWindow());
			load.switchScene("/application/view/MainPage.fxml");
		}
	}
}

