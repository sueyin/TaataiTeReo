package application.model.Question;

import application.controller.MainPageController;
import application.controller.TestPageController;

import java.io.File;
import java.io.IOException;

public class PracticeQuestion extends ClassicQuestion {

    public PracticeQuestion(String number, TestPageController page) {
        super(number, number, page);
    }

    /**
     * Update GUI according to the question status and the result, and record the result
     */
    @Override
    protected void updateGUI(){
        super.updateGUI();
        recordResult();
    }

    /**
     * Record the result to a local record
     */
    private void recordResult(){
        File record = new File(MainPageController.getUser().getDir()+"practice.txt");
        if (!record.exists()){
            try {
                record.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        




    }
}
