package com.xg.androiddemo;

import android.app.Application;
import android.util.Log;

public class Global extends Application {

    protected String tag = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(tag, "onCreate: ");
    }
}
