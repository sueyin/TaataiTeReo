package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PractiseSomething {
	private static Map<String, ArrayList<Boolean>> result = new HashMap<>();

	private static void haha() {
	    File record = new File("./src/application/bank/classic/example.txt");
	    Scanner sc = null;
	    try {
	        sc = new Scanner(record);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    String line;
	    while (sc.hasNextLine()) {
	        line = sc.nextLine();
	        String[] records = line.split("#");
	        String number = records[0];
	        if(records.length == 1){
	            result.put(number, null);
	        }else{
	            String results = records[1];
	            ArrayList<Boolean> marks = new ArrayList<>();
	            for (int i = 0; i < results.length(); i++) {
	                Character c = results.charAt(i);
	                String chara = c.toString();
	                if (chara.equals("1")) {
	                    marks.add(true);
	                } else {
	                    marks.add(false);
	                }
	            }
	            result.put(number, marks);
	        }
	    }
	    sc.close();
	}
	
	public static Map<String, ArrayList<Boolean>> getResult(){
		haha();
		return result;
	}
}
