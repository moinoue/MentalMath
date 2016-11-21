package moinoue.mentalmath;

import android.app.Activity;
import android.os.Bundle;

/*
    This project is essentially just a calculator that uses the user's voice as
    an input and outputs the answer in speech/text display

    Voice -> String -> Tokens -> Evaluation -> String -> Voice

    For the time being, only text input will be allowed until the calculator is
    finished being developed as it isn't really needed until the end
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lexer lexer = new Lexer("log of exponent of 4 plus 5 * 6 square square root root 23.097");
    }
}
