package application.model.Question;

import application.controller.TestPageController;

public class ClassicQuestion extends Question {
    private boolean _tested;


    public ClassicQuestion(String question, String answer, TestPageController page) {
        super(question, answer, page);
        _tested = false;
    }


    @Override
    protected void updateGUI() {
        if (_result){
            _page.rightGUI();
        }else{
            if (!_tested){
                _page.tryAgainGUI();
                _tested = true;
            }else{
                _page.WrongGUI();
            }
        }
    }

}