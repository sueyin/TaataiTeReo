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
                String name = f.getName();
                //Read abstraction
                ArrayList<String> abs = new ArrayList<>();
                String author = sc.nextLine().split("#")[1];
                String desp = sc.nextLine().split("#")[1];
                String total = sc.nextLine().split("#")[1];
                abs.add(author);
                abs.add(desp);
                abs.add(total);
                sc.close();
                suite.put(name, abs);
            }
        }
    }

    private void generateID(){


    }


    /**
     * Write a new Private Question Suite
     */
    public void writePrivateSuite(String id, ArrayList<String> qs){
        String name = id.split("#")[0];
        File newSuite = new File(_private.getPath() + "/" + name + ".txt" );
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(newSuite, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println( "author#" + _usr.getName());
        writer.println( "disp#" + id.split("#")[1]);
        writer.println( "total#" + id.split("#")[2]);
        int index = 1;
        for (String s : qs){
            writer.println(index + "#" + s);
            index++;
        }
        writer.close();
    }

    /**
     * Write a new Public Question Suite
     */
    public void writePublicSuite(String name, ArrayList<String> qs){
        File newSuite = new File(_public.getPath() + "/name.txt" );
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(newSuite, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println( "author#" + qs.get(0));
        writer.println( "disp#" + qs.get(1));
        writer.println( "total#" + qs.get(2));
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
