package application.model.Question;

public class ClassicQuestion extends Question {
    private boolean _tested;
    private boolean _finished;


    public ClassicQuestion(String question, String answer) {
        super(question, answer);
        _tested = false;
    }


    @Override
    protected void updateGUI() {
        if (_result){
            rightGUI();
            _finished = true;
        }else{
            if (!_tested){
                firstWrongGUI();
            }else{
                _tested = true;
                _finished = true;
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

    public boolean isFinished() {
        return _finished;
    }
}