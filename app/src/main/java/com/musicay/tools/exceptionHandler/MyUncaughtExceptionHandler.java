package com.musicay.tools.exceptionHandler;

import android.content.Context;
import android.util.Log;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    public MyUncaughtExceptionHandler(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.i("Log", "uncaughtException: ");
    }
}
