package com.example.events.presenter;

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
