package application.model.question;

import application.controller.MainPageController;
import application.model.file.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClassicQuestionSuite {
    //private static final String BANKDIR = "./src/application/bank/classic/";
    private static final String BANKDIR = "./bank/classic/";
    private static final int TOTAL = 10;

    private String _level;
    private int _index;

    private FileReader _reader;
    private List<String> _answers = new ArrayList<>();
    private Map<String , String> _questionSuite = new HashMap<>();
    private boolean[] _results = new boolean[TOTAL];


    //==================================================================================================================
    public ClassicQuestionSuite(String level) {
        _level = level;
        _index = 0;
        readQuestionBank();
    }
    //==================================================================================================================

    /*
        Manage Questions
     */

    /**
     * Read the corresponding question bank and shuffle the questions randomly.
     */
    protected void readQuestionBank() {
       _reader = new FileReader(BANKDIR + _level + ".txt");
        _questionSuite = _reader.getData();
        for (String s : _questionSuite.keySet()){
            _answers.add(s);
        }
        Collections.shuffle(_answers);
    }

    /**
     * Increase the current index by one. Process to the next question.
     */
    public void nextQuestion(){
        if (_index < TOTAL - 1) {
            _index++;
        }
    }


    //==================================================================================================================


    /*
        Getters
     */

    /**
     * Return the content for the current question
     * @return a String representing the current math question
     */
    public String getQuestion(){
        return _questionSuite.get(_answers.get(_index));
    }

    /**
     * Return the answer for the current question
     * @return the correct answer for the current math question
     */
    public String getAnswer(){
        return _answers.get(_index);
    }

    /**
     * Return the current level (1-15)
     * @return a String representing the current level
     */
    public String getLevel() {
        return _level;
    }

    /**
     * Return the current question index (starting from 1)
     * @return a number representing the current question index
     */
    public int getIndexNumber(){
        return _index + 1;
    }


    //==================================================================================================================


    /*
        Manage Results
     */

    /**
     * Collect result from a question.
     * @param result a boolean value indication the result of a question.
     */
    public void collectResult(boolean result){
        _results[_index]= result;
    }


    /**
     * Return the results of the set of questions
     * @return _results an array of boolean values indicating the result from each quesiton.
     */
    public boolean[] getResults(){
        return _results;
    }


    /**
     * Return the results of the set of questions
     * @return _results an array of boolean values indicating the result from each quesiton.
     */
    public List<String> getAnswers(){
        return _answers;
    }


}