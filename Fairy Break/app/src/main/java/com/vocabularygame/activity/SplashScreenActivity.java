package com.vocabularygame.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.vocabularygame.R;
import com.vocabularygame.manager.Global;
import com.vocabularygame.manager.SoundManager;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView screen_1_logo_imageView, screen_3_imageView;
    private RelativeLayout screen_2_relativeLayout;
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    init();
//                    sleep(2000);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            screen_1_logo_imageView.setVisibility(View.GONE);
//                            screen_2_relativeLayout.setVisibility(View.VISIBLE);
//                            screen_3_imageView.setVisibility(View.GONE);
//                        }
//                    });
                    sleep(2000);
                    if (!isExit) {
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    finish();
                } catch (Exception e) {
                }
            }
        };
        timerThread.start();
    }

    private void init() {
        SoundManager.initSound();
        SoundManager.playBackgroundMusic(this, "background.mp3");
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        Global.screenWidth = size.x;
        Global.screenHeight = size.y;
        Global.ratio = (float) size.x / size.y;

        screen_1_logo_imageView = (ImageView) findViewById(R.id.screen_1_logo_imageView);
        screen_2_relativeLayout = (RelativeLayout) findViewById(R.id.screen_2_relativeLayout);
        screen_3_imageView = (ImageView) findViewById(R.id.screen_3_imageView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            SoundManager.stopBgMusic();
            isExit = true;
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
