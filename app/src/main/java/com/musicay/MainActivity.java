package com.musicay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.github.ybq.android.spinkit.style.ThreeBounce;

public class MainActivity extends AppCompatActivity {

    private MyMusicay myMusicay;
    private ImageButton imageBtn;
    private ImageView imageView;
    private ProgressBar progressBar;
    private String musicURL;
    private ThreeBounce threeBounce;
    boolean first = false;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        myMusicay.setSeekBar(seekBar);
    }

    private void initUI() {
        imageView = findViewById(R.id.imageView);
        imageBtn = findViewById(R.id.imageBtn);
        progressBar = findViewById(R.id.spin_kit);
        seekBar = findViewById(R.id.seekBar);
        musicURL = "http://host2.rjavan.stream/media/mp3/mp3-256/Amorphica-OxyLove-(Ft-Sepehr-Khalse-Pedi-I-Liona).mp3";

        threeBounce = new ThreeBounce();
        myMusicay = new MyMusicay();

        progressBar.setIndeterminateDrawable(threeBounce);
        progressBar.setVisibility(View.INVISIBLE);

        myMusicay.init(musicURL, progressBar, imageBtn, imageView);

    }

    @Override
    protected void onDestroy() {
        myMusicay.killPlayer();
        super.onDestroy();
    }

    public void playPause(View view) {
        if (myMusicay.playOrPause())
            imageBtn.setImageResource(R.drawable.ic_action_play);
        else
            imageBtn.setImageResource(R.drawable.ic_action_pause);

        if (!first) {
            myMusicay.initSeekBar();
            first = true;
        }
    }

}
