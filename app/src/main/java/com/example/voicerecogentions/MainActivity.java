package com.example.voicerecogentions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView res;
    public static final int code =1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = findViewById(R.id.SpeachResult);
        
    }

    public void GetSpeach(View view) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice searching...");
        if(i.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(i,code);
        }
        else {
            Toast.makeText(this,"your Device Doesn't Support Voice Speach Recogention ",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == code)
        {
            if(resultCode == RESULT_OK&& data != null)
            {
                final ArrayList< String > matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (!matches.isEmpty())
                {
                    String Query = matches.get(0);
                    res.setText(Query);
                    //speak.setEnabled(false);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}