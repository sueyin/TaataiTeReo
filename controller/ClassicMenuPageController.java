package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.viewModel.SceneSwitch;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
	private HBox _level1,_level2, _level3, _level4, _level5, _level6, _level7, _level8, _level9, _level10, _level11, _level12, _level13, _level14, _level15;


	@FXML
	public void initialize() {
		List<Integer> Star = new ArrayList<Integer>();
		
		//set stars for 15 levels
		for (int level = 1; level < 16; level++) {
			
			//get result from each level
			String result = MainPageController.getUser().getClassicRecord(Integer.toString(level));
			System.out.println(result);
			
			//determine number of stars awarded to each level
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
		
		//set stars 
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

	/**
	 * Switch to main page when user press return
	 */
	@FXML
	public void handlePressReturn(MouseEvent event) {
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/MainPage.fxml");
	}
	
	/**
	 * Switch to the specific level the user selected
	 */
	@FXML
	public void handlePressLevel(MouseEvent event) {
		_selectedLevel = ((Button) event.getSource()).getText();
		SceneSwitch load = new SceneSwitch((Stage) ((Node) event.getSource()).getScene().getWindow());
		load.switchScene("/application/view/ClassicTestPage.fxml");
	}

	public static String getSelectedLevel(){
		return _selectedLevel;
	}

	public static void setSelectedLevel(String level){
		_selectedLevel = level;
	}
}
