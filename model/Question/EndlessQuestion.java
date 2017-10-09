package application.model.Question;

public class EndlessQuestion extends Question {

    public EndlessQuestion(String question, String answer) {
        super(question, answer);
    }

    @Override
    protected void updateGUI() {
        if (_result)    {
            survivedGUI();
        }else{
            deadGUI();
        }
    }


    private void survivedGUI() {
    }

    private void deadGUI() {
    }

}
