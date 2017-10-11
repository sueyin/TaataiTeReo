package application.model.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MathGenerator {
    private static int[] _primes = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    private static Map<String, ArrayList<String>> _factors = new HashMap<>();
    private static Map<String, ArrayList<String>> _divisors = new HashMap<>();

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
        readFactors();
        ArrayList<String> factors = _factors.get(answer);
        Collections.shuffle(factors);
        return factors.get(0);
    }


    public static String generateDivision(int answer){
        readDivisors();
        ArrayList<String> divisors = _divisors.get(answer);
        return null;
    }


    public static boolean isMultiple(int answer){
        for (int i: _primes){
            if(i == answer){
                return false;
            }
        }
        return true;
    }

    public static boolean divisiable(int answer){
        return (2 <= answer && answer <= 48);
    }

    private static void readFactors(){
        File bank = new File("./src/application/bank/generator/factors.txt");
        try {
            Scanner sc = new Scanner(bank);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String answer = line.split("=")[0];
                String equations = line.split("=")[1];
                ArrayList<String> factors = new ArrayList<>();
                for (String s : equations.split(" ")) {
                    if ((s != null) && (s.trim() != "")) {
                        factors.add(s);
                    }
                    _factors.put(answer, factors);
                }
                sc.close();
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private static void readDivisors(){
        File bank = new File("./src/application/bank/generator/divisors.txt");
        try {
            Scanner sc = new Scanner(bank);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String answer = line.split("=")[0];
                String equations = line.split("=")[1];
                ArrayList<String> factors = new ArrayList<>();
                for (String s : equations.split(" ")) {
                    if ((s != null) && (s.trim() != "")) {
                        factors.add(s);
                    }
                    _factors.put(answer, factors);
                }
                sc.close();
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
