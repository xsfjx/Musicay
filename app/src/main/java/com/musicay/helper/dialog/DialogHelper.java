package com.musicay.helper.dialog;

import android.content.Context;

import com.musicay.tools.view.CustomProgressDialog;

public class DialogHelper {

    private static CustomProgressDialog waitingDialog;

    public static void init(Context context
            , CharSequence title, CharSequence message
            , boolean indeterminate, boolean cancelable) {

        if (waitingDialog != null && waitingDialog.isShowing()) {
            dissmis();
        }
        waitingDialog = new CustomProgressDialog(context
                , title, message
                , indeterminate, cancelable);


    }

    public static void show() {
        waitingDialog.openDialog();
    }

    public static void dissmis() {
        if (waitingDialog != null) {
            waitingDialog.closeDialog();
        }

    }


}
