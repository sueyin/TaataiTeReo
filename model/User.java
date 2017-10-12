package application.model;


import application.TataiApp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {
    private File _classicRecordInit;
    private File _survivalRecord;
    private File _practiceRecord;
    private String _dir;
    private String _name;

    private static Map<String, ArrayList<Boolean>> _practiceStatistic = new HashMap<>();
    private static Map<String, String> _classicStatistic = new HashMap<>();
    private static String _suivivalRecord;

    public User(String name){
        _name = name;
        _dir = TataiApp.getUserDir() + name + "/";
        _practiceRecord = new File(_dir+"practice.txt");
        initializePracticeRecord();
        _classicRecordInit = new File(_dir + "classic.txt");
        initializeClassicRecord();
        _survivalRecord = new File(_dir + "survival.txt");
        initializeSurvivalRecord();
    }


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
                    writer.println(i+"#");
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
        if (!_classicRecordInit.exists()){
            try {
                _classicRecordInit.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Write the default format
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(_classicRecordInit, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            for (int i = 1; i<=15; i++){
                String line = i + "#";
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /*
            Practice Mode
     */

    /**
     * Read the current practice Statistic of the user
     */
    private void readPracticeStatistic(){
        Scanner sc = null;
        try {
            sc = new Scanner(_practiceRecord);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] records = line.split("#");
            String number = records[0];
            if(records.length == 1){
                _practiceStatistic.put(number, null);
            }else{
                String results = records[1];
                ArrayList<Boolean> marks = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    Character c = results.charAt(i);
                    String chara = c.toString();
                    if (chara.equals("1")) {
                        marks.add(true);
                    } else {
                        marks.add(false);
                    }
                }
                _practiceStatistic.put(number, marks);
            }
        }
        sc.close();
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
    public ArrayList<Boolean> getOverallStatistic(){
        readPracticeStatistic();
        ArrayList<Boolean> overall = new ArrayList<>();
        for (String s:_practiceStatistic.keySet()){
            for (Boolean b : _practiceStatistic.get(s) ){
                overall.add(b);
            }
        }
        return overall;
    }


    public void writePractiseRecord(String number, boolean result){
        String path = _dir + "practice.txt";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (String s : _practiceStatistic.keySet()) {
                if (s.equals(number)){
                    _practiceStatistic.get(s).add(result);
                }
                String binary = "";
                for (boolean b : _practiceStatistic.get(s)){
                    if (b){
                        binary = binary + "1";
                    }else{
                        binary = binary + "0";
                    }
                }
                writer.write(s + "#"+ binary + "\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
        Classic Mode
     */

    /**
     * Read the current Classic record of the user
     */
    private void readClassicRecord(){
        //Read the current score
        try {
            Scanner sc = new Scanner(new File(_dir + "classic.txt"));
            while (sc.hasNextLine()) {
                String level = sc.nextLine().split("#")[0];
                String score;
                if (sc.nextLine().split("#").length == 1){
                    score = null;
                }else{
                    score = sc.nextLine().split("#")[1];
                }
                _classicStatistic.put(level, score);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the highest record of a specified level of the user
     */
    public String getClassicRecore(String level){
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
        if (_classicStatistic.get(level) == null){
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
                writer = new PrintWriter(_classicRecordInit, "UTF-8");
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



    /*
        Survival Mode
     */

    /**
     * Read the current highest score, compare with the latest attempt. Update the highest if the score of the latest
     * attempt is higher.
     * @param mode a String representing which record to update
     * @param score a int representing the score of the latest attempt
     */
    private void updateRecord(String mode, String score){
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
        if (current <= Integer.parseInt(score)) {
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

    public void updateSurvivalRecord(String score){
        updateRecord("survival", score);
    }


    /*
        Administration
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

    public String getDir(){
        return _dir;
    }

    public String getName(){
        return _name;
    }
}
