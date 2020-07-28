package com.musicay.main;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.musicay.R;
import com.musicay.helper.messages.MessageHelper;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IMainView {

    private ImageButton imageBtn;
    private ImageView imageView;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private Timer myTimer;
    private SeekBar seekBar;
    private MainPresenter presenter;
    private boolean first = false;
    private boolean mUserIsSeeking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        createPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.viewIsReady();
        initSeekBar();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.viewIsGone();
        sikSeek();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    private void initUI() {
        ThreeBounce threeBounce = new ThreeBounce();
        mediaPlayer = new MediaPlayer();
        imageView = findViewById(R.id.imageView);
        imageBtn = findViewById(R.id.imageBtn);
        progressBar = findViewById(R.id.spin_kit);
        seekBar = findViewById(R.id.seekBar);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        progressBar.setIndeterminateDrawable(threeBounce);
    }

    private void createPresenter() {
        presenter = new MainPresenter();
        presenter.init(this);
    }

    private void initSeekBar() {
        if (mediaPlayer.isPlaying()) {
            seekBar.setMax(mediaPlayer.getDuration());

            myTimer = new Timer();
            myTimer.scheduleAtFixedRate(new TimerTask() {
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

    public void sikSeek() {
        myTimer.cancel();
    }

    public boolean playOrPause() {
        if (mediaPlayer.isPlaying() && mediaPlayer != null) {
            mediaPlayer.pause();
            return true;
        } else {
            mediaPlayer.start();
            return false;
        }
    }

    @Override
    public void playPauseBtnClicked(View view) {
        if (playOrPause())
            imageBtn.setImageResource(R.drawable.ic_action_play);
        else
            imageBtn.setImageResource(R.drawable.ic_action_pause);
        if (!first) {
            initSeekBar();
            first = true;
        }
    }

    @Override
    public void fillPlayer(String... strings) {
        try {
            mediaPlayer.setDataSource(strings[0]);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMsg(String msg) {
        MessageHelper.showMessage(this, msg);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void closeProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void progressbarAnimation(Animation animation) {
        progressBar.startAnimation(animation);
    }

    @Override
    public void imageViewAnimation(Animation animation) {
        imageView.startAnimation(animation);
    }
}
