package application.model.Question;

public class ClassicQuestion extends Question {
    private boolean _tested;


    public ClassicQuestion(String question, String answer) {
        super(question, answer);
        _tested = false;
    }


    @Override
    protected void updateGUI() {
        if (_result){
            rightGUI();
        }else{
            if (!_tested){
                _finished = true;
                firstWrongGUI();
            }else{
                _tested = true;
                secondWrongGUI();
            }
        }
    }


    private void rightGUI(){

    }

    private void firstWrongGUI(){

    }

    private void secondWrongGUI(){

    }





}
