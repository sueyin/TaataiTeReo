package application.model;

import application.TataiApp;
import application.controller.MainPageController;
import application.model.file.FileReader;
import application.model.question.MathGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomManager {
    private File _public;
    private File _private;
    private Map<String, ArrayList<String>> _publicSuites = new HashMap<>();
    private Map<String, ArrayList<String>> _privateSuites = new HashMap<>();
    private User _usr;

    public CustomManager(){
        _usr = MainPageController.getUser();
        _public = new File(TataiApp.getPublicCustomDir());
        _private = new File(TataiApp.getCustomDir()+ _usr.getName() + "/");
        readStorage(_public, _publicSuites);
        readStorage(_private, _privateSuites);
    }

    /**
     * Read the specified question storage directory and store information into the corresponding field.
     */
    private void readStorage(File dir, Map<String, ArrayList<String>> suite ){
        if (!dir.exists()){
            dir.mkdir();
        }
        //Read private storage
        if (dir.listFiles().length < 1){
            suite = null;
        }else{
            for (File f : dir.listFiles()){
                //Create a scanner
                Scanner sc = null;
                try {
                    sc = new Scanner(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //Read the question suite file
                String id = f.getName().substring(0, 7);
                //Read abstraction
                ArrayList<String> abs = new ArrayList<>();
                String author = sc.nextLine().split("#")[1];
                String desp = sc.nextLine().split("#")[1];
                String total = sc.nextLine().split("#")[1];
                abs.add(author);
                abs.add(desp);
                abs.add(total);
                sc.close();
                suite.put(id, abs);
            }
        }
    }




    public Map<String, String> readQuestionSuite(String id, boolean isPublic){
        String path;
        if (isPublic){
            path = _public.getPath() + id + ".txt";
        }else{
            path = _private.getPath() + id + ".txt";
        }
        FileReader reader = new FileReader(path);
        Map<String, String> questionSuite = reader.getData();
        System.out.println("try"+questionSuite.get("author"));
        System.out.println("try"+questionSuite.get("disp"));
        System.out.println("try"+questionSuite.get("total"));
        questionSuite.remove("author");
        questionSuite.remove("disp");
        questionSuite.remove("total");
        return questionSuite;
    }

    /**
     * Write a new question Suite
     */
    public void writeCustomSuite(String id, String desp, ArrayList<String> qs, boolean isPublic){
        File newSuite;
        if (isPublic){
            newSuite = new File(_public.getPath() + "/" + id + ".txt" );
        }else{
            newSuite = new File(_private.getPath() + "/" + id + ".txt" );
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(newSuite, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println( "author#" + _usr.getName());
        writer.println( "disp#" + desp);
        writer.println( "total#" + qs.size());
        for (String s : qs){
            writer.println(s);
        }
        writer.close();
    }


    public Map<String, ArrayList<String>> getPublicSuites() {
        return _publicSuites;
    }


    public Map<String, ArrayList<String>> getPrivateSuites() {
        return _privateSuites;
    }
}
