package com.example.my5gmonitor.Util;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}