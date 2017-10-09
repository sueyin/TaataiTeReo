package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginPageController {
	
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
	
	private List<String> _data = new ArrayList<String>(); //data extracted from txt file............................
	
	private ObservableList<String> _items; 
	
	private static String _selectedUser; //the user that is going to be used in the game
	
	
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
		
		//initially set the warning message to invisible, only displayed when user attemp to continue without select user
		_selectUserMessage.setVisible(false);
	}
	
	/**
	 * This method changes from select user page to add user page
	 */
	@FXML
	public void handlePressAddUser(MouseEvent event) {
		System.out.println("add user");
		
		//initialize the text field for entering use name and warning message to empty 
		_addUserMessage.setText("");
		_textField.setText("");
		
		//switch to add user page
		showSelectUserPage(false);
	}
	
	
	/**
	 * This method deletes the selected user when delete button is pressed
	 */
	@FXML
	public void handlePressDeleteUser(MouseEvent event) {
		System.out.println("delete user"); // need to implement delete user from the store file.................
		String toDelete = _userList.getSelectionModel().getSelectedItem();
		_items.remove(toDelete);
	}

	
	/**
	 * This method switches from select user page to main page when user presses continue button and a user if selected
	 */
	@FXML
	public void handlePressContinueFromSelectUser(MouseEvent event) {
		System.out.println("Enter Main page from Select User Page");
			
		//get current selected user from the list
		_selectedUser = _userList.getSelectionModel().getSelectedItem();
		
		//check if user selected a user, if not, then shows warning message
		if (_selectedUser == null) {
			Service delay = new DelayDisappear();
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
			try {
	        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/MainPage.fxml"));
	        	Scene scene = new Scene(parent);
	        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        	stage.setScene(scene);
	        	stage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	/**
	 * This method changes from add user page to main page if the entered user name is valid 
	 */
	@FXML
	public void handlePressContinueFromAddUser(MouseEvent event) {
		Service delay = new DelayDisappear();
		
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
			_addUserMessage.setText("Please enter a username");
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
		//check if the entered user name already exists, if so, then asks the user to enter another one
		else if (repeated == true) {
			_addUserMessage.setText("Username already exist, please choose another one");
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
	        try {
	        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/MainPage.fxml"));
	        	Scene scene = new Scene(parent);
	        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        	stage.setScene(scene);
	        	stage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			System.out.println("Enter Main page from Add User Page");
		}
	}
	
	
	
	/**
	 * This method changes from the add user page to select user page when the return button is pressed
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		System.out.println("return");
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
	}
	
	/**
	 * This method initializes the user list when launching this page. It gets data on existing users from txt file
	 */
	private void initialiseUserList(){
		_data.add("UserA");//get data for the user list from store file, need to be implemented later.........
		_data.add("UserB");
		_data.add("UserC");
		_data.add("UserD");
		
		
		_items =FXCollections.observableArrayList (_data);
		_userList.setItems(_items); 
		
		//Enables the delete button when an item is selected from the user list
		_userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        System.out.println("Selected item: " + newValue);
		        _delete.setVisible(true);
		    }
		});
	}
	
	
	/**
	 * saves a user name to the list and also file containing all the user name
	 */
	private void saveNewUserName(String username) {
		_items.add(username);
		// need to implement save user name to file later .................................
	}
	
	public static String getSelectedUser() {
		return _selectedUser;
	}
}

/**
 * This class is a background thread for displaying warning message for a period of time and then disable the message
 */
class DelayDisappear extends Service<Void> {
	private static final int MESSAGE_DISPLAY_TIME = 1500;
	
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	//delay the disable warning message from a period of time 
                Thread.sleep(MESSAGE_DISPLAY_TIME);
                return null;
            }
        };
    }
}
