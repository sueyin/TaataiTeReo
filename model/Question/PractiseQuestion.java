package application.model.Question;

import application.controller.TestPageController;

public class PractiseQuestion extends Question {

    public PractiseQuestion(String number, TestPageController page) {
        super(number, number, page);
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
