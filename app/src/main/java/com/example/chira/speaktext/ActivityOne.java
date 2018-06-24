package com.example.chira.speaktext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends Activity implements OnInitListener {
    EditText ee;
    Button b1;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ee=(EditText)findViewById(R.id.editText1);
        b1=(Button)findViewById(R.id.button3);
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String str=ee.getText().toString();
                tts.speak(str,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        Intent i =new Intent();
        i.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(i, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                tts= new TextToSpeech(this,this);
            }
            else
            {
                Intent i=new Intent();
                i.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(i);
            }
        }
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if(status==TextToSpeech.SUCCESS)
        {
            Toast.makeText(getApplicationContext(), "Engine Installed",1000).show();
        }
        if(status==TextToSpeech.ERROR)
        {
            Toast.makeText(getApplicationContext(), "Engine not Installed", 1000).show();
        }
    }
}