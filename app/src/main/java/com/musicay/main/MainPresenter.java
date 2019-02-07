package com.musicay.main;

import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.musicay.R;
import com.musicay.factory.BusApplication;
import com.musicay.factory.interfaces.AsyncWorkerListener;

class MainPresenter {

    private IMainView view;
    private AsyncTaskRunner listener;

    MainPresenter() {

    }

    void init(IMainView view) {
        this.view = view;
        listener = new AsyncTaskRunner();
        listener.onStart("http://host2.rjavan.stream/media/mp3/mp3-256/Amorphica-OxyLove-(Ft-Sepehr-Khalse-Pedi-I-Liona).mp3");
    }

    void viewIsReady() {
        listener.onFinished();
    }

    void viewIsGone() {
    }

    class AsyncTaskRunner implements AsyncWorkerListener<String> {

        @Override
        public void onStart(String... strings) {
            view.showProgress();
            view.fillPlayer(strings);
        }

        @Override
        public void onFinished() {
            view.progressbarAnimation(AnimationUtils.loadAnimation(BusApplication.busContext(), R.anim.fade_out));
            view.imageViewAnimation(AnimationUtils.loadAnimation(BusApplication.busContext(), R.anim.fade_in));
            view.showMsg("LOADED !!!");
            view.closeProgress();
        }

        @Override
        public void onException(String exceptionMsg) {
            Log.i("AFK", "onException: " + exceptionMsg);
        }
    }
}
