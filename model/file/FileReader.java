package application.model.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
    private static final String MULTIPLE = "\u00D7";
    private static final String DIVIDE = "\u00F7";

    private File _file;
    private Map<String, String> _data = new HashMap<>();

    public FileReader(String path){
        _file = new File(path);
        if (!_file.exists()){
            try {
                _file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            _data = null;
        }else{
            readData();
        }
    }

    private void readData(){
        //Create a scanner
        Scanner sc = null;
        try {
            sc = new Scanner(_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Read the Record
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String key = line.split("#")[0];
            String content = translate(line.split("#")[1]);
            _data.put(key, content);
        }
        sc.close();
    }

    /*
        Getters
     */
    public Map<String, String> getData(){
        return _data;
    }


    /*
		Additional
	 */
    /**
     * Translate multiplication and division signs to UTF code
     */
    public static String translate(String raw){
        String translated = "";
        if (raw.length() > 0) {
            for (char i : raw.toCharArray()) {
                if (i == '*') {
                    translated = translated + MULTIPLE;
                } else if (i == '/') {
                    translated = translated + DIVIDE;
                } else {
                    translated = translated + i;
                }
            }
        }
        return translated;
    }

}
