package application.model.question;

import application.TataiApp;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;

public class CustomeQuestion {

    public CustomeQuestion(String equation) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        //TODO associate this with a user
        File custome = new File(TataiApp.getCustomDir());

        //TODO decide whether it is an integer
        /*
        try {
            op1 = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            System.out.println("Wrong number");
            op1 = 0;
        }
        */
        //TODO the eval returns String
        System.out.println(engine.eval(equation));
    }
}



