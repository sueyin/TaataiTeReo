package application.model.question;

import application.controller.TestPageController;

public class OneChanceQuestion extends Question {

    public OneChanceQuestion(String question, String answer, TestPageController page) {
        super(question, answer, page);
    }

    @Override
    protected void updateGUI() {
        if (_result){
            _page.rightGUI();
        }else{
            _page.wrongGUI();
        }
    }
}
