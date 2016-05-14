package com.example.dori.failsafeforreal;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.appinvite.PreviewActivity;

public class MainScreen extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;


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

        //Danger Button Declaration
        ImageButton dangerButton = (ImageButton) findViewById(R.id.dangerButton);
        dangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangerButton(v);
            }
        });

        //EditMText Field
        EditText mTextField = (EditText) findViewById(R.id.mTextField);
        mTextField.setFocusable(false);
        mTextField.setClickable(false);

    }

    //Settings Button Interaction
    protected void settingsButton(View v){
        startActivity(new Intent(MainScreen.this, MainSettingsPage.class));
    }

    //MapButton Interaction
    protected void mapButton(View v){
        startActivity(new Intent(MainScreen.this, mapView.class));
    }

    //All Danger Button interaction
    boolean isPlay = false;
    boolean bool = false;


    protected void dangerButton(View v) {
        ImageButton dangerButton = (ImageButton) findViewById(R.id.dangerButton);
        //Switch images depending on current image

        if (isPlay) {
            dangerButton.setImageResource(R.drawable.dangerimage);
            isPlay = !isPlay;
        }
        else {
            bool=activateActions();
            if(!bool)
            {
                isPlay = !isPlay;
            }
        }
        }


    boolean active = false;
    boolean finished = false;
    protected boolean activateActions(){
        final ImageButton dangerButton = (ImageButton) findViewById(R.id.dangerButton);


        //Set-up Alert Dialog and Text
        AlertDialog.Builder confirmation = new AlertDialog.Builder(MainScreen.this);
        confirmation.setTitle("Activate Danger Signal?");

        confirmation.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelActivation();
                active = false;
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dangerButton.setImageResource(R.drawable.activatedimage);
                active = true;
            }
        });

        AlertDialog alertDialog = confirmation.create();
        alertDialog.show();


        return active;
    }

    boolean valid = false;
    protected void cancelActivation(){
            final AlertDialog.Builder confirmation = new AlertDialog.Builder(MainScreen.this);
            confirmation.setTitle("Password Required");

            final EditText et = new EditText(MainScreen.this);

            et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

            confirmation.setView(et);

            confirmation.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });


            AlertDialog alertDialog = confirmation.create();
            alertDialog.show();
        }

    protected void turnOffActions(){

    }


}
