package application.model.Question;

import application.TataiApp;
import application.controller.ClassicMenuPageController;
import application.controller.MainPageController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomeQuestionSuite{
     private String _path;
     private Map<Integer, String[]> questions = new HashMap<>();
     private ArrayList<Integer> keys = new ArrayList<>();

    public CustomeQuestionSuite(String id, boolean isPublic) {
        if (isPublic) {
            _path = TataiApp.getPublicCustomDir() + id + ".txt";
        } else {
            _path = TataiApp.getCustomDir() + MainPageController.getUser() + id + ".txt";
        }
        readBank();
    }

    private void readBank(){
        File bank = new File(_path);
        try {
            Scanner sc = new Scanner(bank);
            //Ignore the first three lines (abstraction)
            sc.nextLine();
            sc.nextLine();
            sc.nextLine();

            String line;
            int index = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] question = new String[2];
                question[0] = line.split("#")[0];
                question[1] = Question.translate(line.split("#")[1]);
                questions.put(index, question);
                keys.add(index);
                index++;
            }
            sc.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }







}
