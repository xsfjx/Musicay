package com.musicay.main;

import android.view.View;
import android.view.animation.Animation;

public interface IMainView {

    void showMsg (String msg);

    void showProgress ();

    void closeProgress ();

    void setProgressbarVisibility(int visibility);

    void setImageViewVisibility(int visibility);

    void progressbarAnimation(Animation animation);

    void imageViewAnimation(Animation animation);

    void playPauseBtnClicked(View view);

    void fillPlayer(String... strings);
}
