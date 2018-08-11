package com.musicay;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class MainActivity extends AppCompatActivity {

    private MyMusicay myMusicay;
    private ImageButton imageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        imageBtn = findViewById(R.id.imageBtn);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        ThreeBounce threeBounce = new ThreeBounce();
        progressBar.setIndeterminateDrawable(threeBounce);
        progressBar.setVisibility(View.INVISIBLE);

        String musicURL = "http://host2.rjavan.stream/media/mp3/mp3-256/Amorphica-OxyLove-(Ft-Sepehr-Khalse-Pedi-I-Liona).mp3";
        myMusicay = new MyMusicay();
        myMusicay.init(musicURL, progressBar, imageBtn, imageView);
    }

    @Override
    protected void onDestroy() {
        myMusicay.killPlayer();
        super.onDestroy();
    }

    public void btnClick(View view) {
        if (myMusicay.playOrPause())
            imageBtn.setImageResource(R.drawable.ic_action_play);
        else
            imageBtn.setImageResource(R.drawable.ic_action_pause);
    }
}
