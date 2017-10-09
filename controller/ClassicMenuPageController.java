package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClassicMenuPageController {
	
	private static String _selectedLevel;
	
	@FXML
	public void handlePressReturn(MouseEvent event) {
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
	
	@FXML
	public void handlePressLevel1(MouseEvent event) {
		_selectedLevel = "1";
		System.out.println("level1");
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void handlePressLevel2(MouseEvent event) {
		_selectedLevel = "2";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel3(MouseEvent event) {
		_selectedLevel = "3";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel4(MouseEvent event) {
		_selectedLevel = "4";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel5(MouseEvent event) {
		_selectedLevel = "5";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel6(MouseEvent event) {
		_selectedLevel = "6";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel7(MouseEvent event) {
		_selectedLevel = "7";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel8(MouseEvent event) {
		_selectedLevel = "8";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel9(MouseEvent event) {
		_selectedLevel = "9";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel10(MouseEvent event) {
		_selectedLevel = "10";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel11(MouseEvent event) {
		_selectedLevel = "11";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel12(MouseEvent event) {
		_selectedLevel = "12";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel13(MouseEvent event) {
		_selectedLevel = "1";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel14(MouseEvent event) {
		_selectedLevel = "14";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void handlePressLevel15(MouseEvent event) {
		_selectedLevel = "15";
		try {
        	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ClassicTestPage.fxml"));
        	Scene scene = new Scene(parent);
        	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}	
	
	public static String getSelectedLevel(){
		return _selectedLevel;
	}

}
