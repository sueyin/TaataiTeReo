package application.model.question;

import application.controller.MainPageController;
import application.controller.TestPageController;


public class PracticeQuestion extends ClassicQuestion {

    public PracticeQuestion(String number, TestPageController page) {
        super(number, number, page);
    }

    /**
     * Update GUI according to the question status and the result, and record the result
     */
    @Override
    protected void updateGUI(){
        if (_result){
            _page.rightGUI();
            _page.addScore();
            System.out.println("before update " + _question + _result);
            MainPageController.getUser().updatePractiseRecord(_question, _result);
        }else{
            if (!_tested){
                _page.tryAgainGUI();
                _tested = true;
            }else{
                _page.wrongGUI();
                System.out.println("before update " + _question + _result);
                MainPageController.getUser().updatePractiseRecord(_question, _result);
            }
        }
    }

}
