package com.example.dori.failsafeforreal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //Settings Button Declaration
        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingsImageButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                settingsButton(v);
            }
        });

        final Button mapButton = (Button) findViewById(R.id.nearybyAlertsButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mapButton(v);
            }
        });

    }

    protected void settingsButton(View v){
        startActivity(new Intent(MainScreen.this, MainSettingsPage.class));
    }

    protected void mapButton(View v){
        startActivity(new Intent(MainScreen.this, mapView.class));
    }



}
