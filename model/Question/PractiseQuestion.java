package application.model.Question;

public class PractiseQuestion extends Question {


    public PractiseQuestion(String number) {
        super(number, number);
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
