package application.model.question;

import application.controller.TestPageController;

public class ClassicQuestion extends Question {
    protected boolean _tested;

    public ClassicQuestion(String question, String answer, TestPageController page) {
        super(question, answer, page);
        _tested = false;
    }

    /**
     * Update GUI according to the question status and the result
     */
    @Override
    protected void updateGUI() {
        if (_result){
            _page.rightGUI();
            _page.addScore();
        }else{
            if (!_tested){
                _page.tryAgainGUI();
                _tested = true;
            }else{
                _page.wrongGUI();
            }
        }
    }

}