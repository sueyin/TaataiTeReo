package application.model.Question;

import application.controller.TestPageController;

public class PractiseQuestion extends Question {


    public PractiseQuestion(String number, TestPageController page) {
        super(number, number, page);
    }

    @Override
    protected void updateGUI() {
        if (_result){
            rightGUI();
        }else{
            wrongGUI();
        }
    }


    private void rightGUI() {
    }


    private void wrongGUI() {
    }

}
