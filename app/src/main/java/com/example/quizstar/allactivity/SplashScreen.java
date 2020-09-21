package com.example.quizstar.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.quizstar.R;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

public class SplashScreen extends AppCompatActivity {

    private SharedPrefrences shPrfs;
    private String user_emailid;
    private String userpassword;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        shPrfs = SharedPrefrences.getsharedprefInstance(SplashScreen.this);
        videoView = findViewById(R.id.videoView);
       /* MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);*/


        String pat = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(pat);
        videoView.setVideoURI(uri);
        /*MediaController mediaControler = new MediaController(this);
        mediaControler.setAnchorView(videoView);
        videoView.setMediaController(mediaControler);*/
        videoView.start();

        try {
            user_emailid = shPrfs.getUserEmailId();
            userpassword = shPrfs.getUserPassword();
        } catch (Exception e) {

        }


   /*     videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                Toast.makeText(SplashScreen.this, "play", Toast.LENGTH_SHORT).show();
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user_emailid != null && !user_emailid.equalsIgnoreCase("") && userpassword != null && !userpassword.equalsIgnoreCase("")) {
                    Intent intent = new Intent(SplashScreen.this, NavigationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }

            }
        }, 10000);

    }


}