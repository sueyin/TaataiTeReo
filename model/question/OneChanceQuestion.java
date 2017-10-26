package application.model.question;

import application.controller.TestPageController;

/**
 * This class models questions that the user can only attempt once (as in the survival mode).
 */
public class OneChanceQuestion extends Question {

    public OneChanceQuestion(String question, String answer, TestPageController page) {
        super(question, answer, page);
    }

    @Override
    protected void updateGUI() {
        if (_result){
            _page.addScore();
            _page.rightGUI();
        }else{
            _page.wrongGUI();
        }
    }
}
