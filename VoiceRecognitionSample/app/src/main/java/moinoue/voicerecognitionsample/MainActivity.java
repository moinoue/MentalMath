package moinoue.voicerecognitionsample;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.speech.RecognizerIntent;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/*
    This is a sample just using the build in android library to produce a string
    by using the users input through the help of Google's Speech recognition
 */


public class MainActivity extends Activity {

    private TextView displayInput;
    private final int REQUIRED_SPEECH_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button micButton;

        setContentView(R.layout.activity_main);
        //Hook on mainActivity widgets
        displayInput  = (TextView)findViewById(R.id.syntax_text);
        micButton     = (Button)findViewById(R.id.microphone_button);

        micButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

    }

    private void promptSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.info_text));
        try{
            startActivityForResult(intent, REQUIRED_SPEECH_CODE);
        }catch(ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUIRED_SPEECH_CODE){
            if(resultCode == RESULT_OK && data != null){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                try {
                    Expression expression = new ExpressionBuilder(result.get(0)).build();
                    double rlt = expression.evaluate();
                    displayInput.setText(Double.toString(rlt));//(result.get(0));
                }
                catch(Exception e){
                    displayInput.setText(e.toString());//(result.get(0));
                }
                //Grab what the user said and display it
                //displayInput.setText(Double.toString(rlt));//(result.get(0));
            }
        }
    }
}
