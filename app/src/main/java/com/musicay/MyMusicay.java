package com.musicay;

import android.media.AudioManager;
import android.media.MediaPlayer;
import java.io.IOException;

public class MyMusicay {

    private MediaPlayer mediaPlayer;

    MyMusicay(String myURL) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(myURL);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean playOrPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            return true;
        } else {
            mediaPlayer.start();
            return false;
        }
    }

    public void killPlayer() {
        mediaPlayer.release();
    }

}

