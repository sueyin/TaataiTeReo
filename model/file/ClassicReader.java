package application.model.file;

import application.controller.MainPageController;

import java.util.*;

public class ClassicReader  {
    private static final String BANKDIR = "./src/application/bank/classic/";
    //private static final String BANKDIR = "./bank/classic/";
    private FileReader _reader;

    //Question relate
    private Set<String> _answers = new HashSet<>();
    private Map<String, String> _questionSuite = new HashMap<>();

    //Record relate
    private String _recordDir;
    private Map<String, String> _record = new HashMap<>();


    public ClassicReader(String level) {
        _recordDir = MainPageController.getUser().getDir();
        readQuestion(level);
        readRecord();
    }

    private void readQuestion(String level){
        _reader = new FileReader(BANKDIR + level + ".txt");
        _questionSuite = _reader.getData();
        _answers = _questionSuite.keySet();
    }

    private void readRecord(){
        _reader = new FileReader(_recordDir);
        _record =  _reader.getData();
    }



    /*
        Getters
     */

    //Question relate
    public Set<String> getAnswers() {
        return _answers;
    }

    public Map<String, String> getQuestions() {
        return _questionSuite;
    }

    public Map<String, String> getRecord() {
        return _record;
    }
}
