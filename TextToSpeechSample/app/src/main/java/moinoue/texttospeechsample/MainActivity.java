package moinoue.texttospeechsample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;

/*
    This is a sample of text-to-speech by taking in the user's input into the
    text box field then reading it out loud through the help of Google's Text-to-Speech
 */

public class MainActivity extends Activity {

    private EditText displayInput;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button speakButton;
        //Hooking on to the widgets in the main_activity
        displayInput = (EditText)findViewById(R.id.editText);
        speakButton     = (Button)findViewById(R.id.speakButton);

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status){
                if(status == TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        speakButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    activateSpeechOver20();
                }else {
                    activateSpeechSub21();
                }
            }
        });
    }

    //Different versions of speak are called for firmware below or equal to Lollipop
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void activateSpeechOver20(){
        try {
            textToSpeech.speak(displayInput.getText().toString(), TextToSpeech.QUEUE_FLUSH, null,"");
        }catch(ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressWarnings("deprecation")
    private void activateSpeechSub21(){
        try {
            textToSpeech.speak(displayInput.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        }catch(ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.not_supported),
                    Toast.LENGTH_SHORT).show();
    }
    }
}
