package application.model;

import application.TataiApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

    public CustomManager(User usr){
        _usr = usr;
        _public = new File(TataiApp.getPublicCustomDir());
        _private = new File(TataiApp.getCustomDir()+ usr.getName() + "/");
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
                String id = f.getName();
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


    /**
     * Write a new Question Suite
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
