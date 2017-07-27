package com.vocabularygame.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.vocabularygame.R;
import com.vocabularygame.manager.PlayGameManager;
import com.vocabularygame.manager.SoundManager;

public class PlayGameActivity extends AppCompatActivity {

    private PlayGameManager playGameManager;
    private TextView word_textView, timeText;

    private int counterTime = 45;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        SoundManager.initSound();
        SoundManager.playBackgroundMusic(this, "45_sec_timer.mp3");


        playGameManager = PlayGameManager.getInstance(this);
        word_textView = (TextView) findViewById(R.id.word_textView);
        timeText = (TextView) findViewById(R.id.timeText);
        word_textView.setText(playGameManager.randomWord());

        playTimer();
    }


    public void nextBtnClick(View view) {
        SoundManager.playAudio(this, "click.wav");
        word_textView.setText(playGameManager.playNext());
    }

    public void stopBtnClick(View view) {
        SoundManager.playAudio(this, "click.wav");
        counterTime = 45;
        countDownTimer.cancel();
        SoundManager.stopBgMusic();
        finish();
    }

    private void playTimer() {
        countDownTimer = new CountDownTimer(46000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counterTime--;
                timeText.setText(String.format("%02d", counterTime));
                if (counterTime == 0) {
                    counterTime = 45;
                    cancel();
                    SoundManager.stopBgMusic();
                    SoundManager.playAudio(PlayGameActivity.this, "wrong_choice.wav");
                    SoundManager.mediaPlayerAudio = null;
                    finish();
                }
            }
            @Override
            public void onFinish() {
                counterTime = 0;
            }
        };
        countDownTimer.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            SoundManager.stopBgMusic();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

