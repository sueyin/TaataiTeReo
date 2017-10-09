package application.model;


import application.TataiApp;

import java.io.*;
import java.util.Scanner;

public class User {
    private File classicRecordInit;
    private File endlessRecord;
    private File survivalRecord;
    private String _dir;
    private String _name;

    public User(String name){
        _name = name;
        _dir = TataiApp.getUserDir() + name + "/";
        classicRecordInit = new File(_dir + "classic1.txt");
        endlessRecord = new File(_dir + "endless.txt");
        survivalRecord = new File(_dir + "survival.txt");
    }


    /**
     * Read the current highest score, compare with the latest attempt. Update the highest if the score of the latest
     * attempt is higher.
     * @param mode a String representing which record to update
     * @param score a int representing the score of the latest attempt
     */
    private void updateRecord(String mode, int score){
        //Check if the file exist (if it is the first try)
        File target = new File(_dir + mode + ".txt");
        if (!target.exists()){
            try {
                target.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Read the current score
        int current = 0;
        try {
            Scanner sc = new Scanner(target);

            while (sc.hasNextLine()) {
                current = Integer.parseInt(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Update the score if it is higher
        if (current <= score) {
            try {
                PrintWriter writer = new PrintWriter(target, "UTF-8");
                writer.println(score);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


    public void updateClassicRecord(int level, int score){
        updateRecord("classic" + level, score);
    }


    public void updateEndlessRecord(int score){
        updateRecord("endless", score);
    }


    public void updateSurvivalRecord(int score){
        updateRecord("survival", score);
    }










}
