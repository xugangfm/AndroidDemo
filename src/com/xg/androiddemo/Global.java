package com.xg.androiddemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class Global extends Application {

    public  static Context appContext;
    protected String tag = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Log.d(tag, "onCreate: ");
    }

   public static Context  getAppContext(){return appContext;}
}
