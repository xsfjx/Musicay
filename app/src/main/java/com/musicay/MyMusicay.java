package com.musicay;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.musicay.MyMusicay.mediaPlayer;

public class MyMusicay {

    static MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private boolean mUserIsSeeking = false;

    public void init(String musicURL, ProgressBar progressBar, ImageButton button, ImageView imageView) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
        asyncTaskRunner.setProgressBar(progressBar);
        asyncTaskRunner.setButton(button);
        asyncTaskRunner.setImageView(imageView);
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

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    public void initSeekBar() {
        if (mediaPlayer.isPlaying()) {
            seekBar.setMax(mediaPlayer.getDuration());
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }, 0, 1);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int userSelectedPosition = 0;

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    mUserIsSeeking = true;
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        userSelectedPosition = progress;
                    }
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mUserIsSeeking = false;
                    mediaPlayer.seekTo(userSelectedPosition);
                }
            });
        }
    }

    public void killPlayer() {
        mediaPlayer.release();
    }

}

class AsyncTaskRunner extends AsyncTask<String, String, String> {

    private ProgressBar progressBar;
    private ImageButton button;
    private ImageView imageView;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setButton(ImageButton button) {
        this.button = button;
    }

    protected void setProgressBar(ProgressBar progressBar) {
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
        imageView.setVisibility(View.VISIBLE);
        Animation expandIn = AnimationUtils.loadAnimation(MyApplication.context, R.anim.fade_in);
        Animation expandOut = AnimationUtils.loadAnimation(MyApplication.context, R.anim.fade_out);
        progressBar.startAnimation(expandOut);
        imageView.startAnimation(expandIn);
        button.startAnimation(expandIn);
    }

}

