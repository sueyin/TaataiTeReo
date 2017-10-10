package application.model.Question;

import application.controller.TestPageController;

public class ClassicQuestion extends Question {
    private boolean _tested;
    private boolean _finished;


    public ClassicQuestion(String question, String answer, TestPageController page) {
        super(question, answer, page);
        _tested = false;
    }


    @Override
    protected void updateGUI() {
        if (_result){
            _page.rightGUI();
            _finished = true;
        }else{
            if (!_tested){
                _page.tryAgainGUI();
            }else{
                _tested = true;
                _finished = true;
                _page.WrongGUI();
            }
        }
    }



    public boolean isFinished() {
        return _finished;
    }
}