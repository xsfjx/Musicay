package com.musicay.factory.interfaces;

public interface AsyncWorkerListener<E> {

    void onStart(String... string);
    void onFinished();
    void onException(String exceptionMsg);
}
