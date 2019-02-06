package com.musicay.tools.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;

import com.musicay.R;
import com.musicay.tools.exceptionHandler.MyUncaughtExceptionHandler;

public class CustomProgressDialog extends ProgressDialog {

    private static CustomProgressDialog mdialog;

    private CustomProgressDialog(Context context) {
        super(context, R.style.AppThemeDark);
    }

    public CustomProgressDialog(Context context
            , CharSequence title, CharSequence message
            , boolean indeterminate, boolean cancelable) {
        super(context, R.style.AppThemeDark);

        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(getContext()));
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        init(context, title, message, indeterminate, cancelable);

    }

    private static void init(Context context
            , CharSequence title, CharSequence message
            , boolean indeterminate, boolean cancelable) {

        mdialog = new CustomProgressDialog(context);
        mdialog.setTitle(title);
        mdialog.setIndeterminate(indeterminate);
        mdialog.setMessage(message);
        mdialog.setCancelable(cancelable);
        mdialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mdialog.getWindow().setGravity(Gravity.CENTER);

    }

    public void closeDialog() {
        if (mdialog != null && mdialog.isShowing()) {
            mdialog.dismiss();
            mdialog = null;
        }
    }

    public void openDialog() {
        if (mdialog != null)
            mdialog.show();

    }


}

