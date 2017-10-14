package application.model.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClassicQuestionSuite {
    private static final String BANKDIR = "./bank/classic/";
    private static final int TOTAL = 10;
    private String _level;
    private HashMap<String, String> _questionSuite = new HashMap<>();

    private int _index;
    private List<String> _answerList = new ArrayList<>();

    private boolean[] _results = new boolean[TOTAL];

    public ClassicQuestionSuite(String level) {
        _level = level;
        _index = 0;
        readQuestionBank();
        Arrays.fill(_results, Boolean.FALSE);
    }


    /*
        Manage Questions
     */

    /**
     * Read the corresponding question bank and shuffle the questions randomly.
     */
    protected void readQuestionBank() {
        File bank = new File(BANKDIR + _level + ".txt");
        try {
            Scanner sc = new Scanner(bank);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String answer = line.split("#")[0];
                String question = Question.translate(line.split("#")[1]);
                System.out.println(answer +" " + question);
                _questionSuite.put(answer, question);
                _answerList.add(answer);
            }
            sc.close();
            Collections.shuffle(_answerList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Increase the current index by one. Process to the next question.
     */
    public void nextQuestion(){
        if (_index < TOTAL - 1) {
            _index++;
        }
    }



    /*
        Getters
     */

    /**
     * Return the content for the current question
     * @return a String representing the current math question
     */
    public String getQuestion(){
        return _questionSuite.get(_answerList.get(_index));
    }

    /**
     * Return the answer for the current question
     * @return the correct answer for the current math question
     */
    public String getAnswer(){
        return _answerList.get(_index);
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




    /*
        Manage Results
     */

    /**
     * Collect result from a question.
     * @param result a boolean value indication the result of a question.
     */
    public void collectResult(boolean result){
        _results[_index] = result;
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
        return _answerList;
    }


}