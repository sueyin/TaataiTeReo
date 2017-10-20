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
            _page.addScore();
        }else if (_read.length() < 1){
            _page.emptyRecordGUI();
        }else{
            _page.wrongGUI();
        }
    }
}
