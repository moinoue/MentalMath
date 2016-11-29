package moinoue.mentalmath;

/**
 * Copyright (c) 2016 Vidhya, Mohammed
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

/*
    This project is essentially just a calculator that uses the user's voice as
    an input and outputs the answer in speech/text display

    Voice -> String -> Tokens -> Evaluation -> Error Handling -> String -> Voice

    For the time being, only text input will be allowed until the calculator is
    finished being developed as it isn't really needed until the end
 */
public class MainActivity extends Activity {

    private EditText inputText;
    private TextView answerText;
    private TextView heardText;
    private Lexer lexer;
    private Parser parser;
    private ErrorHandling errorHandling;
    private TextToSpeech textToSpeech;

    private final int REQUIRED_SPEECH_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button   micButton;
        Button   solveButton;


        //Hook on to widgets to modify later during runtime
        inputText  = (EditText)findViewById(R.id.inputTextbox);
        heardText  = (TextView)findViewById(R.id.heardText);
        answerText  = (TextView)findViewById(R.id.answerText);
        micButton     = (Button)findViewById(R.id.micButton);
        solveButton     = (Button)findViewById(R.id.answerButton);

        micButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

        solveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                solveClick();
            }
        });

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status){
                if(status == TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.US);
                    activateSpeech("Welcome to Mental Math. Speak or type a simple math problem.");
                }
            }
        });

    }
    private void promptSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.input_text));
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
                heardText.setText(result.get(0));
                lexer = new Lexer(heardText.getText().toString());
                errorHandling = new ErrorHandling();
                parser = new Parser(lexer,errorHandling);

                inputText.setText(lexer.output());
                answerText.setText(parser.print());


                if (errorHandling.hasError()){
                    answerText.setText(errorHandling.getErrors());
                    System.out.println("Error found");
                    return;
                }

                activateSpeech(answerText.getText().toString());
            }
        }
    }

    private void solveClick(){
        heardText.setText("");
        lexer = new Lexer(inputText.getText().toString());
        errorHandling = new ErrorHandling();
        parser = new Parser(lexer,errorHandling);

        answerText.setText(parser.print());

        if (errorHandling.hasError()){
            answerText.setText(errorHandling.getErrors());
            return;
        }


        activateSpeech(answerText.getText().toString());
    }

    private void activateSpeech(String speechString){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activateSpeechOver20(speechString);
        }else {
            activateSpeechSub21(speechString);
        }
    }

    //Different versions of speak are called for firmware below or equal to Lollipop
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void activateSpeechOver20(String speechString){
        try {
            textToSpeech.speak(speechString, TextToSpeech.QUEUE_FLUSH, null,"");
        }catch(ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("deprecation")
    private void activateSpeechSub21(String speechString){
        try {
            textToSpeech.speak(speechString, TextToSpeech.QUEUE_FLUSH, null);
        }catch(ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

}
