package com.vocabularygame.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vocabularygame.R;
import com.vocabularygame.dialog.SearchWordDialog;
import com.vocabularygame.manager.SoundManager;
import com.vocabularygame.manager.ViewExtras;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= 23) {
            ArrayList<String> requestList = new ArrayList<>();

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (requestList.size() > 0) {
                String[] requestArr = new String[requestList.size()];
                requestArr = requestList.toArray(requestArr);
                ActivityCompat.requestPermissions(this, requestArr, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        boolean isGratedFully = true;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                isGratedFully = false;
                break;
            }
        }

        if (!isGratedFully) {
            new MaterialDialog.Builder(this)
                    .title(R.string.app_name)
                    .titleColor(ViewExtras.getColor(this, R.color.colorPrimary))
                    .content("Can not grant permission fully!")
                    .positiveText("Exit")
                    .positiveColor(ViewExtras.getColor(this, R.color.colorPrimary))
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            dialog.cancel();
                            MainActivity.this.finish();
                        }
                    })
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
                    .show();
        }
    }

    public void searchWordClick(View view) {
        SoundManager.playAudio(this, "click.wav");
        SearchWordDialog searchWordDialog = new SearchWordDialog(this);
        searchWordDialog.show_Dialog();
    }

    public void playGameClick(View view) {
        SoundManager.playAudio(this, "click.wav");
        SoundManager.stopBgMusic();
        Intent intent = new Intent(this, PlayGameActivity.class);
        startActivity(intent);
    }

    public void infoGameClick(View view) {
        SoundManager.playAudio(this, "click.wav");
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fairybreadgames.com"));
        startActivity(browserIntent);
    }

    public void exitGameClick(View view) {
        SoundManager.playAudio(this, "click.wav");
        SoundManager.stopBgMusic();
        finish();
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
