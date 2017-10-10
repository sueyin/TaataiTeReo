package application.model.Question.generator;

public class MathGenerator {

    public static String generateAddition(int answer) {
        //int range = (max - min) +1
        //int a = (int)(Math.random()* range + min)
        int a = (int)(Math.random() * (answer - 1) + 1);
        int b = answer - a;
        return a + " + " + b;
    }

    public static String generateSubstraction(int answer, int max) {
        //max =99
        int a = (int) (Math.random() * (max - answer + 1) + 1);
        int b = a - answer;
        return a + " - " + b;

    }

    public static String generateMultiplication(int answer){
        return null;
    }
}
