package application.model.question;

import java.util.HashMap;

/**
 * This class provides the functionality of converting integers (1 - 99) to String in Maori.
 */
public class Answer {

    private enum Number {
        ONE(1, "tahi"), TWO(2, "rua"), THREE(3, "toru"), FOUR(4, "whaa"), FIVE(5, "rima"),
        SIX(6, "ono"), SIVE(7, "whitu"), EIGHT(8, "waru"), NINE(9, "iwa");
        private int _n;
        private String _read;

        Number(int n, String read){
            _n = n;
            _read = read;
        }
        public int getNumber(){
            return _n;
        }
        public String getRead(){
            return _read;
        }
    }

    private int _n;
    private String _answer;
    private HashMap<Integer, String> answerset = new HashMap<Integer, String>();

    public Answer(int n){
        _n = n;
        for (Number num : Number.values()){
            answerset.put(num.getNumber(), num.getRead());
        }
        generateAnswer();
    }

    /**
     *Genereate the Answer of the given number. Store the answer in a String.
     */
    private void generateAnswer(){
        if (_n < 10){
            _answer = answerset.get(_n);
        }else if (_n == 10) {
            _answer = "tekau";
        }else if (_n < 20){
            int one = _n % 10;
            _answer = "tekau maa " + answerset.get(one);
        }else if (_n % 10 == 0){
            _answer = answerset.get(_n/10)+" tekau";
        }else{
            int ten = _n / 10;
            int one = _n - 10 * ten;
            _answer = answerset.get(ten) + " tekau maa " + answerset.get(one);
        }
    }

    public String getAnswer() {
        return _answer;
    }

}
