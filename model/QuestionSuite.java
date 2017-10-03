package application.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuestionSuite {
    private static final int MAX = 10;
    private static final int EASY_LIMIT= 9;
    private static final int HARD_LIMIT= 99;

    private boolean _isEasy;

    private int _index;
    private List<Integer> _numbers = new ArrayList<>();
    private boolean[] _results ;

    /**
     * Generate numbers for questions according to easy/hard level
     * @param e indicates easy/hard level user chose.
     */
    public QuestionSuite(boolean e){
        _index = 0;
        _results = new boolean[MAX];
        _isEasy = e;
        generateRandomNumbers();
    }

    /**
     * Generate required amount of numbers according to easy/hard level
     */
    private void generateRandomNumbers(){
        int range;
        if (_isEasy){
            range = EASY_LIMIT;
        }else{
            range = HARD_LIMIT;
        }
        for (int i = 1; i <= range; i++){
            _numbers.add(i);
        }
        if ( range < MAX){
            for (int i = range; i < MAX ; i++) {
                int extra = (int)(( Math.random()) * range) + 1;
                _numbers.add(extra);
            }
        }
        Collections.shuffle(_numbers);
       _numbers =  _numbers.subList(0, MAX);
       System.out.println(_numbers);
    }


    /**
     * Return the current question index starting from 0
     * @return a number representing the current question index
     */
    public int getIndex(){
        return _index;
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
        if (_index < MAX - 1) {
            _index++;
        }
    }

    /**
     * Return the number for the current question
     * @return a number that the user is going to read
     */
    public int getNumber(){
        return _numbers.get(_index);
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
    public List<Integer> getNumbers(){
        return _numbers;
    }

    
    public boolean isEasy(){
    	return _isEasy;
    }

}
