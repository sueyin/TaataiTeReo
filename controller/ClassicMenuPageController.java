package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.model.question.ClassicQuestion;
import application.model.question.ClassicQuestionSuite;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClassicMenuPageController {
	
	private static String _selectedLevel;
	
	@FXML
	private ImageView _star1;
	@FXML
	private ImageView _star2;
	@FXML
	private ImageView _star3;
	
	@FXML
	private HBox _level1;
	
	@FXML
	private HBox _level2;
	
	@FXML
	private HBox _level3;
	
	@FXML
	private HBox _level4;
	
	@FXML
	private HBox _level5;
	
	@FXML
	private HBox _level6;
	
	@FXML
	private HBox _level7;
	
	@FXML
	private HBox _level8;
	
	@FXML
	private HBox _level9;
	
	@FXML
	private HBox _level10;
	
	@FXML
	private HBox _level11;
	
	@FXML
	private HBox _level12;
	
	@FXML
	private HBox _level13;
	
	@FXML
	private HBox _level14;
	
	@FXML
	private HBox _level15;
	

	@FXML
	public void initialize() {
		List<Integer> Star = new ArrayList<Integer>();
		for (int level = 1; level < 16; level++) {
			String result = MainPageController.getUser().getClassicRecord(Integer.toString(level));
			System.out.println(result);
			if (result.equals("")) {
				Star.add(0);
			}
			else{
				int score = Integer.parseInt(result);
				//display no star if between 0-2
				if (score < 2) {
					Star.add(0);
				}
				//display 1 star is between 2-4
				else if (score < 5) {
					Star.add(1);
				}
				//display 2 stars if between 5 -8
				else if (score < 9) {
					Star.add(2);
				}
				//display 3 stars if between 9-10
				else {
					Star.add(3);
				}
			}
		}
		HBox[] box = {_level1, _level2, _level3, _level4, _level5, _level6, _level7, _level8, _level9, _level10, _level11, _level12, _level13, _level14, _level15};
		for (int i = 0; i < 15; i++) {
			int num = Star.get(i);
			box[i].setId(""+num);
		}
		
		for (HBox b: box){
			if (b.getId().equals("0")){
				b.setVisible(false);
				ObservableList list = b.getChildren();
				
			}
			else if (b.getId().equals("1")) {
				Node node = b.getChildren().get(1);
				node.setVisible(false);
				node = b.getChildren().get(2);
				node.setVisible(false);
			}
			else if (b.getId().equals("2")) {
				Node node = b.getChildren().get(2);
				node.setVisible(false);
			}
		}
	}

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

	public static void setSelectedLevel(String level){
		_selectedLevel = level;
	}
}
