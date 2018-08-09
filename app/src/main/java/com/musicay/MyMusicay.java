package com.musicay;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

import static com.musicay.MyMusicay.mediaPlayer;

public class MyMusicay{

    static MediaPlayer mediaPlayer;

    public void init(String musicURL , ProgressBar progressBar , Button button){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
        asyncTaskRunner.setProgressBar(progressBar);
        asyncTaskRunner.setButton(button);
        asyncTaskRunner.execute(musicURL);
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

class AsyncTaskRunner extends AsyncTask<String, String, String> {

    private ProgressBar progressBar;
    private Button button;

    public void setButton(Button button) {
        this.button = button;
    }

    protected void setProgressBar (ProgressBar progressBar){
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            mediaPlayer.setDataSource(strings[0]);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
    }

}

