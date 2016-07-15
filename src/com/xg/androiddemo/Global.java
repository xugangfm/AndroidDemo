package com.xg.androiddemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class Global extends Application {

    static int count = 0;
    public  static Context appContext;
    protected String tag = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Log.i(tag, "onCreate: "+(count++) +"次");
        Log.i(tag, "onCreate, pid="+android.os.Process.myPid());

//        Intent it =new Intent(this, RemoteService.class);
//        startService(it);
    }


    public static Context  getAppContext(){return appContext;}
}
