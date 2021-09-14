package com.moringaschool.mywordfinder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.mywordfinder.R;

public class splachActivity extends AppCompatActivity {

    //
    private  static  int SPLASH_SCREEN=5000;
    ImageView image;
    TextView logo,slogon;
    //animation variables
    Animation topAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splach);
        //animation
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        image=findViewById(R.id.logoImage);
        logo=findViewById(R.id.logoText);
        slogon=findViewById(R.id.sloganText);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogon.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splachActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}