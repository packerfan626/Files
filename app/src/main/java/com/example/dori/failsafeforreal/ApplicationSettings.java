package com.example.dori.failsafeforreal;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class ApplicationSettings extends AppCompatActivity {

    public static boolean saver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_settings);

        final Button batterySaverButton = (Button) findViewById(R.id.batterySaverButton);
        assert batterySaverButton != null;
        batterySaverButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                changer();

                View backgroundimage = findViewById(R.id.relativelayoutid);
                Drawable background = backgroundimage.getBackground();
                if (saver) {
                    batterySaverButton.setText("BATTERY SAVER ON");
                    background.setAlpha(255);
                } else {
                    batterySaverButton.setText("BATTERY SAVER OFF");
                    background.setAlpha(0);
                }

            }
        });

    }

    public void changer() {
        if (saver == false) {
            saver = true;
            HelpAndSupport.saver = true;
            MainScreen.saver = true;
            MainSettingsPage.saver = true;
            mapView.saver = true;
        } else {
            saver = false;
            HelpAndSupport.saver = false;
            MainScreen.saver = false;
            MainSettingsPage.saver = false;
            mapView.saver = false;
        }
    }
}
