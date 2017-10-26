package application.model.survival;

import java.util.*;

/**
 * This class generates random equations (for the survival mode). It can generate 14 types of equations of different
 * difficulties. The answer of every equation and numbers appeared in every equation are integers between 1 - 99.
 */
public class MathGenerator {
    private static final String MULTIPLE = "\u00D7";
    private static final String DIVIDE = "\u00F7";

    private static int[] _primes = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    private static Map<String, ArrayList<String>> _factors = new HashMap<>();
    private static Map<String, ArrayList<String>> _divisors = new HashMap<>();


    public MathGenerator(){
        readFactors();
        readDivisors();
    }

    //================================================================================

    /*
        Basic Math generators
     */

    /**
     * Given an integer, randomly generate a addition equation whose result is the integer.
     */
    public static String generateAddition(int answer) {
        //int range = (max - min) +1
        //int a = (int)(Math.random()* range + min)
        int a = (int) (Math.random() * (answer - 1) + 1);
        int b = answer - a;
        return a + "+" + b;
    }

    /**
     * Given an integer, randomly generate a substraction equation whose result is the integer.
     */
    public static String generateSubstraction(int answer, int max) {
        int a = (int) (Math.random() * (max - answer) + answer + 1);
        int b = a - answer;
        return a + "-" + b;
    }

    /**
     * Given an integer, randomly generate a multiplication equation whose result is the integer.
     */
    public static String generateMultiplication(int answer){
        readFactors();
        ArrayList<String> factors = _factors.get(Integer.toString(answer));
        Collections.shuffle(factors);
        return factors.get(0);
    }

    /**
     * Given an integer, randomly generate a division equation whose result is the integer.
     */
    public static String generateDivision(int answer){
        readDivisors();
        ArrayList<String> divisors = _divisors.get(Integer.toString(answer));
        Collections.shuffle(divisors);
        return divisors.get(0);
    }

    /**
     * Returns false if the input is a prime, true if not.
     */
    public static boolean isMultiple(int answer){
        for (int i: _primes){
            if(i == answer){
                return false;
            }
        }
        return true;
    }

    /**
     * For every integer between 1 - 99, find its factors (except 1 and itself) and store in a Map.
     */
    private static void readFactors(){
        for (int i = 1; i <= 99; i++){
            ArrayList<String> factors = new ArrayList<>();
            for (int j = 2; j < i; j++){
                if (i%j ==0){
                    factors.add(j + MULTIPLE + i/j);
                }
            }
            _factors.put(Integer.toString(i), factors);
        }
    }

    /**
     * For every integer between 1 - 99, find how it can be get by a division between 1 - 99, and store in a Map.
     */
    private static void readDivisors(){
        for (int i = 2; i <= 48; i++){
            ArrayList<String> divisors = new ArrayList<>();
            for (int j = 99; j > i; j--){
                if (j%i == 0){
                    divisors.add(j + DIVIDE + j/i);
                }
            }
            _divisors.put(Integer.toString(i), divisors);
        }

    }

    //================================================================================

    /*
        Equation generators
     */

    /**
     * A+B with in 1 - 10
     */
    public static String generateBasicA(){
        int answer = (int) (Math.random() * 9 + 2);
        return answer + "#" + generateAddition(answer);
    }

    /**
     * A+B within 1 - 99
     */
    public static String generateBasicAH(){
        int answer = (int) (Math.random() * 98 + 2);
        return  answer + "#" + generateAddition(answer);
    }

    /**
     * A-B within 1 - 10
     */
    public static String generateBasicB(){
        //10 to 1
        int answer = (int) (Math.random() * 10  + 1);
        return  answer + "#" + generateSubstraction(answer, 10);
    }

    /**
     * A-B within 1 - 99
     */
    public static String generateBasicBH(){
        //98 to 1
        int answer = (int) (Math.random() * 98 + 1);
        return  answer + "#" + generateSubstraction(answer, 99);
    }

    /**
     * A+B+C within 6 - 20
     */
    public static String generateCompA(){
        // 20 to 3
        int answer = (int)(Math.random()* 18 + 3);
        //a-1 to 2
        int a = (int)(Math.random() * (answer - 2) + 2);
        int b = answer - a;
        return  answer + "#" + generateAddition(a) + "+" + b;
    }

    /**
     * A+B+C within 1 - 99
     */
    public static String generateCompB(){
        //99 to 3
        int answer = (int)(Math.random()* 97 + 3);
        int a = (int)(Math.random() * (answer - 2) + 2);
        int b = answer - a;
        return  answer + "#" + generateAddition(a) + "+" + b;
    }

    /**
     * A-B-C within 1 - 99
     */
    public static String generateCompC(){
        //97 to 1
        int answer = (int)(Math.random()* 97 + 1);
        //98 to a + 1
        int a = (int)(Math.random() * (98 - answer) + answer + 1);
        int b = a - answer;
        return  answer + "#" + generateSubstraction(a, 99) + "-" + b;
    }

    /**
     * A-B+C or A+B-C within 1 - 99
     */
    public static String generateCompD(){
        int answer = (int)(Math.random()* 98 + 2);
        int b = (int) (Math.random() * (answer - 1) + 1);
        int a = answer - b;
        if ((Math.random() < 0.5)){
            return answer + "#" + generateSubstraction(a, 99) + "+" + b;
        }else{
            return answer + "#" + b + "+" + generateSubstraction(a, 99);
        }
    }

    /**
     * A*B+C or A+B*C within 1 - 99
     */
    public static String generateCompE(){
        int answer = (int)(Math.random()* 98 + 2);
        int a = (int) (Math.random() * (answer - 1) + 1);
        while(!isMultiple(a)){
            a = (int) (Math.random() * (answer - 1) + 1);
        }
        int b = answer - a;
        if ((Math.random() < 0.5)){
            return answer + "#" + generateMultiplication(a) + "+" + b;
        }else{
            return answer + "#" + b + "+" + generateMultiplication(a);
        }
    }

    /**
     * A*B-C within 1 - 99
     */
    public static String generateCompF(){
        //99-2
        int a = (int)(Math.random() * 98 + 2);
        while (!isMultiple(a)) {
            a = (int) (Math.random() *  98 + 2);
        }
        //a-1 to 1
        int b = (int)(Math.random() * (a - 1) + 1);
        int answer = a - b;
        return answer + "#" + generateMultiplication(a) + "-" + b;
    }

    /**
     * C-A*B within 1 - 99
     */
    public static String generateCompG(){
        //98 - 2
        int a = (int)(Math.random() * (97) + 2);
        while (!isMultiple(a)) {
            a = (int) (Math.random() * (97) + 2);
        }
        //99 - a
        int b = (int)(Math.random() * (100 - a) + a);
        int answer = b - a;
        return answer + "#" + b + "-" + generateMultiplication(a);
    }

    /**
     * A/B+C or A+C/B  within 1 - 99
     */
    public static String generateCompH(){
        //48 to 2
        int a = (int)(Math.random() * (47) + 2);
        //99 to a+1
        int answer = (int)(Math.random() * (99-a) + a+1);
        int b = answer - a;
        if ((Math.random() < 0.5)){
            return answer + "#" + b + "+" + generateDivision(a);
        }else{
            return answer + "#" + generateDivision(a) + "+" + b;
        }
    }

    /**
     * A/B-C  within 1- 99
     */
    public static String generateCompI(){
        //48 to 2
        int a = (int)(Math.random() * (47) + 2);
        //a-1 to 1
        int b = (int)(Math.random() * (a - 1) + 1);
        int answer = a - b;
        return answer + "#" + generateDivision(a) + "-" + b;
    }

    /**
     * C-A/B  within 1 - 99
     */
    public static String generateCompJ(){
        //48 to 2
        int a = (int)(Math.random() * (47) + 2);
        //99 to a+1
        int b = (int)(Math.random() * (99 - a) + a + 1);
        int answer = b - a;
        return answer + "#" + b + "-" + generateDivision(a)  ;
    }

}
