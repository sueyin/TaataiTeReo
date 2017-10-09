package application.model.Question;

public class EndlessQuestion extends Question {
    private int _score;

    public EndlessQuestion(String question, String answer) {
        super(question, answer);
        _score = 0;
    }

    @Override
    protected void updateGUI() {
        if (_result){
            continueGUI();
            _score++;
        }else{
            endGUI();
        }
    }


    private void continueGUI() {
    }

    private void endGUI() {
    }

    public int getScore() {
        return _score;
    }
}