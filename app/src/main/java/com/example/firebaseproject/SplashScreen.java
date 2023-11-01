package com.example.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView welcome,sher,studentservice;
    RelativeLayout relativeLayout;
    Animation txtAnimation,layoutAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        txtAnimation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.fall_down);

        layoutAnimation = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.top_bottom);

        welcome= (TextView) findViewById(R.id.welcome);
        sher= (TextView) findViewById(R.id.sher);
        studentservice= (TextView) findViewById(R.id.studentservice);

        relativeLayout = findViewById(R.id.realMain);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout.setAnimation(layoutAnimation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sher.setVisibility(View.VISIBLE);
                        welcome.setVisibility(View.VISIBLE);
                        studentservice.setVisibility(View.VISIBLE);

                        sher.setAnimation(txtAnimation);
                        studentservice.setAnimation(txtAnimation);
                        welcome.setAnimation(txtAnimation);



                    }
                },400);

            }
        },300);

        new  Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,Home.class);
                startActivity(intent);
                finish();
            }
        },800);

    }
}