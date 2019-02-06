package com.musicay.tools.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{

    private static Context context;

    public static Context getContext() {
        return context;
    }

    void setContext() {
        context = getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setContext();
    }
}
