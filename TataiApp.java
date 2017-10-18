package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class TataiApp extends Application {
    private static final String ROOTDIR = "./Tatai/";
    private static final String USR = ROOTDIR +"usr/";
    private static final String CUSTOM = ROOTDIR + "custom/";
    private static final String CUSTOMPUBLIC = ROOTDIR + "custom/.publicpool/";
    private static final String TEMP = ROOTDIR + "temp/";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginPage.fxml"));
        primaryStage.setTitle("Tatai Te Reo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    
    }


    public static void main(String[] args) {
        setup(ROOTDIR);
        setup(USR);
        setup(CUSTOM);
        setup(CUSTOMPUBLIC);
        setup(TEMP);
        launch(args);
    }


    /**
     * Create a directory if it does not already exist.
     * @param dir a String indicating the path of the directory
     */
    private static void setup(String dir){
        File d = new File(dir);
        if (!d.exists()){
            d.mkdir();
        }
    }

    /**
     * Getter fot the path of the root directory
     * @return  a String indicating the path of the root directory
     */
    public static String getRootDir(){
        return ROOTDIR;
    }

    /**
     * Getter for the path of the user directory, where records of users are stored in respective subfolders
     * @return a String indicating the path of the user directory
     */
    public static String getUserDir(){
        return USR;
    }

    /**
     * Getter for the path of the public custom question directory, where all the public custom questions are stored.
     * @return a String indicating the path of the custom question directory
     */
    public static String getPublicCustomDir(){
        return CUSTOMPUBLIC;
    }


    /**
     * Getter for the path of the custom question directory, where all the custom questions are stored.
     * @return a String indicating the path of the custom question directory
     */
    public static String getCustomDir(){
        return CUSTOM;
    }



    /**
     * Getter for the path of the user directory, where records of users are stored in respective subfolders
     * @return a String ndicating the path of the user directory
     */
    public static String getTempDir(){
        return TEMP;
    }



}
