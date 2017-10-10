package application.model.Question.generator;

public class Substraction {
    private final int MAX = 99;

    public Substraction(int answer) {


    }

    protected String generateQuestion(int answer) {
        int a = (int) (Math.random() * (MAX - answer + 1) + 1);
        int b = a - answer;
        return " " + a + " - " + b + " ";

    }
}
