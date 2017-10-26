package application.model.question;

import application.controller.TestPageController;

/**
 * This class models questions that the user can attempt twice. If the user answered the question wrong, it will
 * bring up the try again GUI which allow the user to try the second time.
 */
public class TwoChancesQuestion extends Question {
    protected boolean _tested;

    public TwoChancesQuestion(String question, String answer, TestPageController page) {
        super(question, answer, page);
        _tested = false;
    }

    /**
     * Update GUI according to the question status and the result. If the user said the answer wrong the first time, it
     * will bring up the GUI that allows the user to try again. If the user said the answer wrong the second time, it
     * will bring up the GUI that tells the user the right answer. If the user said the answer right, it will bring up
     * the GUI that tells the u
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