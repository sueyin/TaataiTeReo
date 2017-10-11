package application.model.Question;

import application.controller.MainPageController;

import java.util.*;

public class SurvivalQuestionSuite {
    private Map<String, String> _set = new HashMap<>();
    private ArrayList<String> _questions = new ArrayList<>();
    private int index;

    public SurvivalQuestionSuite(){
        index = 0;
        for (int i = 1; i <= 99; i++){
            _questions.add(Integer.toString(i));
        }
    }


    public void renewQuestionSuite(){
        generateRandomQuestions();
        Collections.shuffle(_questions);
    }


    private void generateRandomQuestions(){
        int i = 0;
        for (int j = 0; j < 10; j++) {
                //A+B
                _set.put(Integer.toString(i), MathGenerator.generateAddition(i));
                i++;
                //A-B
                _set.put(Integer.toString(i), MathGenerator.generateSubstraction(i, 99));
                i++;
        }
        //i=20
        for (int j = 0; j < 8; j++ ){

        }


        /*
        type1  A+B 20
        type2   A-B 20
        type8   A+B+C 5
        type9   A-B+C 5
        type10  A+B-C 5
        type11  A-B-C 5
        type3   A*B+A*B
        type12  A*B+C 8
        type13  A/B+C 8
        type14  A*B-C 5
        type15  A/B-C 5
        type16  C-A*B 5
        type17  C-A/B 5
        type4   A*B-A*B 5
        type5   A/B+A*B 5
        type6   A*B-A/B 5
        type7   A/B-A/B 3




         */

    }


    private void type1(){

    }









}
