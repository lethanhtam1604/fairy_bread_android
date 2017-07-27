package com.vocabularygame.manager;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class SoundManager {

    public static MediaPlayer mediaPlayerAudio = null;
    private static MediaPlayer mediaPlayerBg = null;


    public static void initSound() {
        mediaPlayerAudio = null;
        mediaPlayerBg = null;
    }

    public static void playAudio(Context context, String fileName) {
        try {
            if (mediaPlayerAudio == null) {
                mediaPlayerAudio = new MediaPlayer();
                AssetFileDescriptor afd = context.getAssets().openFd(fileName);
                mediaPlayerAudio.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mediaPlayerAudio.setVolume(1f, 1f);
                mediaPlayerAudio.prepare();
            }
            mediaPlayerAudio.start();
        } catch (Exception e) {

        }
    }

    public static void playBackgroundMusic(Context context, String fileName) {
        try {
            if (mediaPlayerBg == null) {
                mediaPlayerBg = new MediaPlayer();
                AssetFileDescriptor afd = context.getAssets().openFd(fileName);
                mediaPlayerBg.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mediaPlayerBg.setVolume(1f, 1f);
                mediaPlayerBg.setLooping(true);
                mediaPlayerBg.prepare();
            }
            mediaPlayerBg.start();
        } catch (Exception e) {

        }
    }

    public static void stopBgMusic() {
        mediaPlayerBg.stop();
    }
}


