package application.model.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClassicQuestionSuite {
    private static final String BANKDIR = "bank/classic/";
    private static final int TOTAL = 10;
    private int _level;
    private HashMap<String, String> _questionSuite = new HashMap<>();

    private int _index;
    private List<Integer> _answerList = new ArrayList<>();

    private boolean[] _results ;

    public ClassicQuestionSuite(int level) {
        _level = level;
        readQuestionBank();
    }


    /**
     * Read the corresponding question bank and shuffle the questions randomly.
     */
    protected void readQuestionBank() {
        String bankAddress = BANKDIR + Integer.toString(_level) + ".txt";
        File bank = new File(bankAddress);

        try {
            Scanner sc = new Scanner(bank);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String answer = line.split("#")[0];
                String question = line.split("#")[1];
                _questionSuite.put(answer, question);
                _answerList.add(Integer.parseInt(answer));
            }
            sc.close();
            Collections.shuffle(_answerList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public int getLevel() {
        return _level;
    }



    /**
     * Return the current question index starting from 1
     * @return a number representing the current question index
     */
    public int getIndexNumber(){
        return _index + 1;
    }



    /**
     * Increase the current index by one. Process to the next question.
     */
    public void nextQuestion(){
        if (_index < TOTAL - 1) {
            _index++;
        }
    }

    /**
     * Return the answer for the current question
     * @return the correct answer for the current math question
     */
    public int getAnswer(){
        return _answerList.get(_index);
    }

    /**
     * Return the content for the current question
     * @return a String representing the current math question
     */
    public String getQuestion(){
        return _questionSuite.get(Integer.toString(_answerList.get(_index)));
    }


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
    public List<Integer> getAnswers(){
        return _answerList;
    }


}
