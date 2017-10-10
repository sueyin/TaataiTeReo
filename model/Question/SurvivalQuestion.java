package application.model.Question;

import application.controller.TestPageController;

public class SurvivalQuestion extends Question{
    private int _live;
    private int _score;

    public SurvivalQuestion(String question, String answer, TestPageController page) {
        super(question, answer, page);
        _live = 3;
        _score = 0;
    }


    private void generateQuestion(){
        _answer = "2";
        _question = "1+1";
    }


    @Override
    protected void updateGUI() {
        if(_result){
            _score++;
            survivedGUI();
        }else{
            if (_live == 0){
                deadGUI();
            }else{
                _live--;
                wrongGUI();
            }
        }
    }



    private void survivedGUI(){

    }

    private void wrongGUI() {

    }

    private void deadGUI(){

    }

    public boolean isAlive(){
        return (_live > 0);
    }

    public int getScore(){
        return _score;
    }


}