package com.example.zanzibar184.animations;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;

import java.util.Random;


public final class MainActivity extends AppCompatActivity {
    private static int fromX = 0;
    private static int fromY = 0;
    private static int moveX = 0;
    private static int moveY = 0;
    private static int circle_width;
    private static int circle_height;
    private static int height;
    private static int width;
    private static int dx = 1;
    private static int dy = 1;
    private static int v = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View v = findViewById(R.id.circle);
        circle_width = v.getWidth();
        circle_height = v.getHeight();

        this.startViewAnimation(this);

    }


    private void startViewAnimation(final MainActivity actual_activity) {


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int display_height = displayMetrics.heightPixels;
        final int display_width = displayMetrics.widthPixels;

        height = display_height - circle_height;
        width = display_width - circle_width;

        setBounce();
        /*Log.i("a","height: "+ height +
                "moveY: " +moveY);*/
        final MainActivity main = actual_activity;

        //direction
        TranslateAnimation animation = new TranslateAnimation(
                fromX,
                moveX,
                fromY,
                moveY
        );

        //loop
        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                fromX = moveX;
                fromY = moveY;
                main.startViewAnimation(main);
            }
        });

        //keep the final position after every animation
        animation.setFillAfter(true);
        this.findViewById(R.id.circle).startAnimation(animation);


    }

    private void setMove() {
        // random x direction
        moveX = (new Random()).nextInt(width + 1);
        while (moveX > height - fromX) {
            moveX = (new Random()).nextInt(height + 1);
        }
        // moveX =  fromX == width?0:width;


        // random y direction
        moveY = (new Random()).nextInt(height + 1);
        while (moveY > height - fromY) {
            moveY = (new Random()).nextInt(height + 1);
        }
        //moveY = fromY == height?0:height;
    }

    private void setBounce() {
        if (moveX >= width || moveX < 0)
            dx *= -1;
        if (moveY >= height || moveY < 0)
            dy *= -1;

        moveX += 3 * dx * v;
        moveY += 2 * dy * v;


    }


}

