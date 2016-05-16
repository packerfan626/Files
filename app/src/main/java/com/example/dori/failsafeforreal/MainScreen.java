package com.example.dori.failsafeforreal;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.appinvite.PreviewActivity;
import com.google.android.gms.vision.CameraSource;

public class MainScreen extends AppCompatActivity{
    static final int REQUEST_VIDEO_CAPTURE = 1;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //Settings Button Declaration
        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingsImageButton);
        assert settingsButton != null;
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                settingsButton(v);
            }
        });

        final Button mapButton = (Button) findViewById(R.id.nearybyAlertsButton);
        assert mapButton != null;
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mapButton(v);
            }
        });

        //camera view
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView1);

        surfaceHolder = surfaceView.getHolder();
        //Danger Button Declaration
        ImageButton dangerButton = (ImageButton) findViewById(R.id.dangerButton);
        assert dangerButton != null;
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
    protected void settingsButton(View v) {
        startActivity(new Intent(MainScreen.this, MainSettingsPage.class));
    }

    //MapButton Interaction
    protected void mapButton(View v) {
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
        } else {
            bool = activateActions();
            //right here //////////////////////////////////////////////////////////////////////////////////////
            try{
                camera = Camera.open();
            }catch(RuntimeException e){

                return;
            }
//                Camera.Parameters param;
//                param = camera.getParameters();
//                //modify parameter
//                //param.setPreviewFrameRate(20);
//                param.setPreviewSize(176, 144);
//                camera.setParameters(param);
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            } catch (Exception e) {
                return;
            }

            if (!bool) {
                isPlay = !isPlay;
            }
        }
    }


    boolean active = false;
    boolean finished = false;

    protected boolean activateActions() {
        final ImageButton dangerButton = (ImageButton) findViewById(R.id.dangerButton);
        final EditText mTextField = (EditText) findViewById(R.id.mTextField);
        //Set-up Alert Dialog and Text
        final AlertDialog.Builder confirmation = new AlertDialog.Builder(MainScreen.this);
        confirmation.setTitle("Activate Danger Signal?");
        final AlertDialog initial = confirmation.create();
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

        final AlertDialog alertDialog = confirmation.create();
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {

                mTextField.setText(millisUntilFinished / 1000 + " seconds until automatic " +
                        "alert.");
            }

            public void onFinish() {
                alertDialog.dismiss();
                mTextField.setText("");
                dangerButton.setImageResource(R.drawable.activatedimage);
            }

        }.start();


        mTextField.setText("");
        alertDialog.show();

        return active;
    }

    boolean valid = false;

    protected void cancelActivation() {
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

    protected void turnOffActions() {

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////ALL TRIAL STUFF FOR CAMERA /////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////


   /* public class VideoServer extends Activity implements SurfaceHolder.Callback {
        TextView testView;

        Camera camera;
        SurfaceView surfaceView;
        SurfaceHolder surfaceHolder;

        private final String tag = "VideoServer";

        Button start, stop;

        *//** Called when the activity is first created. *//*
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            start = (Button)findViewById(R.id.btn_start);
            start.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View arg0) {
                    start_camera();
                }
            });

            stop = (Button)findViewById(R.id.btn_stop);
            stop.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View arg0) {
                    stop_camera();
                }
            });

            surfaceView = (SurfaceView)findViewById(R.id.surfaceView1);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        private void start_camera() {
            try{
                camera = Camera.open();
            }catch(RuntimeException e){
                Log.e(tag, "init_camera: " + e);
                return;
            }
            Camera.Parameters param;
            param = camera.getParameters();
            //modify parameter
            param.setPreviewFrameRate(20);
            param.setPreviewSize(176, 144);
            camera.setParameters(param);
            try {
                camera.setPreviewDisplay((SurfaceHolder) surfaceView);
                camera.startPreview();
            } catch (Exception e) {
                Log.e(tag, "init_camera: " + e);
                return;
            }
        }

        private void stop_camera() {
            camera.stopPreview();
            camera.release();
        }

        public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        public void surfaceCreated(SurfaceHolder holder) {
            // TODO Auto-generated method stub
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO Auto-generated method stub
        }
    }*/
}
