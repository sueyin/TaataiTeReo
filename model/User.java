package application.model;


import application.TataiApp;
import application.model.file.FileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {
    private File _practiceRecord;
    private File _classicRecord;
    private File _survivalRecord;
    //TODO add exp and acheivement systems
    private File _e;
    private File _achivs;

    private String _practiceRecPath;
    private String _classicRecPath;
    private String _survivalRecPath;


    private String _dir;
    private String _name;

    private FileReader _reader;

    private Map<String, ArrayList<Boolean>> _practiceStatistic = new HashMap<>();
    private Map<String, String> _classicStatistic = new HashMap<>();
    private String _survivalScore;

    private int _exp;

    public User(String name){
        _name = name;
        _dir = TataiApp.getUserDir() + name + "/";
        _practiceRecPath = _dir + "practice.txt";
        _classicRecPath = _dir + "classic.txt";
        _survivalRecPath = _dir + "survival.txt";
        _practiceRecord = new File(_practiceRecPath);
        initializePracticeRecord();
        _classicRecord = new File(_classicRecPath);
        initializeClassicRecord();
        _survivalRecord = new File(_survivalRecPath);
        initializeSurvivalRecord();
    }

    //==================================================================================================================
    //==================================================================================================================

    /*
        Initialization
     */

    /**
     * Create the Practice record file and write the default format if not exists
     */
    private void initializePracticeRecord(){
        //Check if the record file exists
        if(!_practiceRecord.exists()){
            try {
                _practiceRecord.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Write default format
            try {
                PrintWriter writer = new PrintWriter(_dir + "practice.txt", "UTF-8");
                for (int i = 1; i<=99; i++){
                    writer.println(i+"#-");
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create the Classic record file and write the default format if not exists.
     */
    private void initializeClassicRecord(){
        //Create the record file if not exist
        if (!_classicRecord.exists()){
            try {
                _classicRecord.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Write the default format
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(_classicRecord, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            for (int i = 1; i <= 15; i++){
                String line = i + "#-";
                writer.println(line);
            }
            writer.close();
        }
    }

    /**
     * Create the Survival record file if not exists
     */
    private void initializeSurvivalRecord(){
        if(!_survivalRecord.exists()){
            try {
                _survivalRecord.createNewFile();
                PrintWriter writer = new PrintWriter(_classicRecord, "UTF-8");
                writer.println("0");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //==================================================================================================================
    //==================================================================================================================

    /*
            Practice Mode
     */

    /**
     * Read the current practice Statistic of the user
     */
    private void readPracticeStatistic(){
        _reader = new FileReader(_practiceRecPath);
        Map<String, String> record = _reader.getData();
        for (String s : record.keySet()){
            if (s.equals("-")){
                //Put null if there is no record
                _practiceStatistic.put(s, null);
            }else{
                //Put true or false if there is a record
                _practiceStatistic.put(s, stringTranslate(record.get(s)));
            }
        }
    }

    /**
     * Write the newest record to the local record file
     */
    public void updatePractiseRecord(String number, boolean result){
        readPracticeStatistic();

        //Update the new statistic
        _practiceStatistic.get(number).add(result);

        //Write the newest statistic
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(_practiceRecord, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (String s : _practiceStatistic.keySet()) {
            if (_practiceStatistic.get(s) != null) {
                String binary = booleanTranslate(_practiceStatistic.get(s));
                writer.println(s + "#" + binary);
            } else {
                writer.println(s + "#" + "-");
            }
        }
        writer.close();
    }

    /**
     * Return the statistic for a specified number
     */
    public ArrayList<Boolean> getStatistic(String number){
        readPracticeStatistic();
        return _practiceStatistic.get(number);
    }

    /**
     * Return the statistic for the overall performance
     */
    public Map<String, ArrayList<Boolean>> getOverallStatistic(){
    	readPracticeStatistic();
        return _practiceStatistic;

    }



    //==================================================================================================================

    /**
     * Supports the updatePractiseRecord() method. Translate the ArrayList<Boolean> to a String
     */
    private String booleanTranslate(ArrayList<Boolean> stats){
        String result = "";
        for (boolean b : stats){
            if (b){
                result = result + "1";
            }else{
                result = result + "0";
            }
        }
        return result;
    }

    /**
     * Supports the updatePractiseRecord() method. Translate the  String to an ArrayList<Boolean>
     */
    private ArrayList<Boolean> stringTranslate(String read){
        ArrayList<Boolean> marks = new ArrayList<>();
        for (char c : read.toCharArray()){
            if (c == '1'){
                marks.add(true);
            }else{
                marks.add(false);
            }
        }
        return marks;
    }



    //==================================================================================================================
    //==================================================================================================================



    /*
        Classic Mode
     */

    /**
     * Read the current Classic record of the user
     */
    private void readClassicRecord(){
        _reader = new FileReader(_classicRecPath);
        _classicStatistic = _reader.getData();
    }

    /**
     * Return the highest record of a specified level of the user
     */
    public String getClassicRecord(String level){
       readClassicRecord();
       return _classicStatistic.get(level);
    }

    /**
     * Update the specified level if the current score is higher than the past record
     */
    public void updateClassicRecord(String level, String score){
        //Read the most current classic record
        readClassicRecord();
        boolean flag = false;
        //Decide whether the current score is greater than the last record
        if (_classicStatistic.get(level).equals("-")){
            flag = true;
        }else{
            int last = Integer.parseInt(_classicStatistic.get(level));
            if (Integer.parseInt(score) > last){
                flag = true;
            }
        }

        //Update the record
        if (flag){
            _classicStatistic.remove(level);
            _classicStatistic.put(level, score);
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(_classicRecord, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            for (String s : _classicStatistic.keySet()){
                writer.println(s + "#" + _classicStatistic.get(s));
            }
            writer.close();
        }
    }



    //==================================================================================================================
    //==================================================================================================================



    /*
        Survival Mode
     */

    private void readSurvivalScore(){
        try {
            Scanner sc = new Scanner(_survivalRecord);
            _survivalScore = sc.nextLine();
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the current highest score, compare with the latest attempt. Update the highest if the score of the latest
     * attempt is higher.
     * @param score a int representing the score of the latest attempt
     */
    private void updateSurvivalScore(String score){
        readSurvivalScore();
        //Update the score if it is higher
        int before = Integer.parseInt(_survivalScore);
        int now = Integer.parseInt(score);
        if (before < now ) {
            try {
                PrintWriter writer = new PrintWriter(_survivalRecord, "UTF-8");
                writer.println(score);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String getSurvivalScore() {
        readSurvivalScore();
        return _survivalScore;
    }


    //==================================================================================================================
    //==================================================================================================================



    /*
        Administration
     */

    /**
     * Delete the user foler and its content
     */
    public void deleteUser(){
        File dir = new File (_dir);
        if(dir.exists()){
            for (File f : dir.listFiles()){
                f.delete();
            }
            dir.delete();
        }
    }

    /**
     * Return the path of the user folder
     */
    public String getDir(){
        return _dir;
    }

    /**
     * Return the user's name
     */
    public String getName(){
        return _name;
    }

    /**
     * Return the current exp value of the user
     */
    public int getExp() {
        return _exp;
    }

    /**
     * Increase the exp value of the user
     */
    public void increaseExp(int exp){
        _exp = _exp + exp;
    }


}
