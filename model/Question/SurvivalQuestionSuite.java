package application.model.question;

import java.util.*;

public class SurvivalQuestionSuite {
    private ArrayList<String> _questions = new ArrayList<>();
    private MathGenerator _generator;
    private int _phase;
    private int _index;

    public SurvivalQuestionSuite(){
        _generator = new MathGenerator();
        _phase = 1;
        renewQuestions();
    }

    private void renewQuestions() {
        _questions.clear();
       if (_phase == 1){  //6
           for (int i = 0; i < 3; i++){
               _questions.add(_generator.generateBasicA());
               _questions.add(_generator.generateBasicB());
           }
       }else if (_phase == 2){ //8
           for (int i = 0; i < 2; i++){
               _questions.add(_generator.generateBasicA());
               _questions.add(_generator.generateBasicB());
               _questions.add(_generator.generateBasicAH());
               _questions.add(_generator.generateBasicBH());
           }
       }else if (_phase == 3){  //9
            for (int i = 0; i < 3; i++){
                _questions.add(_generator.generateBasicAH());
                _questions.add(_generator.generateBasicBH());
                _questions.add(_generator.generateCompA());
                _questions.add(_generator.generateCompC());
            }
       }else{   //10
           _questions.add(_generator.generateCompA());
           _questions.add(_generator.generateCompB());
           _questions.add(_generator.generateCompC());
           _questions.add(_generator.generateCompD());
           _questions.add(_generator.generateCompE());
           _questions.add(_generator.generateCompF());
           _questions.add(_generator.generateCompG());
           _questions.add(_generator.generateCompH());
           _questions.add(_generator.generateCompI());
           _questions.add(_generator.generateCompJ());
       }
        Collections.shuffle(_questions);
    }

    public void nextQuestion(){
        if (_index == _questions.size() - 1){
            _phase++;
            _index = 0;
            renewQuestions();
        }else{
            _index++;
        }
    }

    public String  getQuestion(){
        return _questions.get(_index).split("#")[1];
    }

    public String getAnswer(){
        return _questions.get(_index).split("#")[0];
    }

}
