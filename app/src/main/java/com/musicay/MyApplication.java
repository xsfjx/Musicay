package com.musicay;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
