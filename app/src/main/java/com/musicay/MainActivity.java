package com.musicay;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;
    private MyMusicay myMusicay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);
        String musicURL = "http://host2.rjavan.stream/media/mp3/mp3-256/Mahbod-Tangna.mp3";
        myMusicay = new MyMusicay(musicURL);
    }

    @Override
    protected void onDestroy() {
        myMusicay.killPlayer();
        super.onDestroy();
    }

    public void btnClick(View view) {
        if (myMusicay.playOrPause()) {
            btn.setText(R.string.resume);
            Snackbar.make(view, "Music is Paused , Exit?", Snackbar.LENGTH_LONG)
                    .setAction("Yes", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    })
                    .show();
        } else {
            btn.setText(R.string.pause);
            Snackbar.make(view, "Music is Played.", Snackbar.LENGTH_LONG)
                    .setAction(":)", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textView.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setVisibility(View.INVISIBLE);
                                }
                            }, 3000L);
                        }
                    })
                    .show();
        }
    }
}
