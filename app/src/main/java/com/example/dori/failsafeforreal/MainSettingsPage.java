package com.example.dori.failsafeforreal;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainSettingsPage extends AppCompatActivity {
    public static boolean saver = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings_page);

        Button applicationButton = (Button) findViewById(R.id.applicationButton);
        applicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSettingsPage.this, ApplicationSettings.class));
            }
        });

        Button accountButton = (Button) findViewById(R.id.accountButton);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button helpButton = (Button) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSettingsPage.this, HelpAndSupport.class));
            }
        });

        Button termsButton = (Button) findViewById(R.id.termsButton);
        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        View backgroundimage = findViewById(R.id.settingslayout);
        Drawable background = backgroundimage.getBackground();
        if(saver){

            background.setAlpha(255);
        }
        else{
            background.setAlpha(0);
        }

    }
}
