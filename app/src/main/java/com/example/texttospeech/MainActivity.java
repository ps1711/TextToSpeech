package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText enteredvalue;
    Button speakButton;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speakButton=findViewById(R.id.SpeakButton);
        enteredvalue=findViewById(R.id.TextValue);
        textToSpeech=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                textToSpeech.setLanguage(Locale.UK);
                }
                else
                {
                    Log.e("failed","OnInit:Failed");
                }
            }
        });
     speakButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String text=enteredvalue.getText().toString();
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                 textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
                 Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
             } else {
                 textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
             }
         }
     });
    }
}