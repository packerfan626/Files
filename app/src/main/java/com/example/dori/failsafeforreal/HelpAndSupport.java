package com.example.dori.failsafeforreal;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpAndSupport extends AppCompatActivity {

    public static boolean saver = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //////////////////////////////////////
        setContentView(R.layout.activity_help_and_support);
        View backgroundimage = findViewById(R.id.helpsupportlayout);
        Drawable background = backgroundimage.getBackground();
        if(saver){

            background.setAlpha(255);
        }
        else{
            background.setAlpha(0);
        }
    }
}
