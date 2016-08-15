package com.xg.androiddemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class Global extends Application  implements Application.ActivityLifecycleCallbacks{

    static int count = 0;
    public  static Context appContext;
    protected String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        registerActivityLifecycleCallbacks(this);
        Log.i(TAG, "onCreate: "+(count++) +"次");
        Log.i(TAG, "onCreate, pid="+android.os.Process.myPid());

//        Intent it =new Intent(this, RemoteService.class);
//        startService(it);
        //testUri();

        try {
            PackageInfo packageInfo = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(),0);

            String packageName = packageInfo.packageName;
            String  versionName = packageInfo.versionName;
            int versionCode = packageInfo.versionCode;


            int uid = appContext.getPackageManager().getApplicationInfo(appContext.getPackageName(), 0).uid;
            ApplicationInfo appinfo = appContext.getApplicationInfo();
            Log.i(TAG,appinfo.toString());

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }


    public static Context  getAppContext(){return appContext;}

    public void testUri(){

        Uri uri = Uri.parse("rong://"+getAppContext().getClass().getCanonicalName()).buildUpon().appendPath("xugang").appendPath("Book").appendQueryParameter("name","java").build();
        Log.e("Uri", uri.toString() );
        Log.e("getAuthority", uri.getAuthority());
        Log.e("getHost", uri.getHost());
        Log.e("getPathSegments", uri.getPathSegments().toString());
        Log.e("getPath", uri.getPath());
        Log.e("getQueryParameter", uri.getQueryParameter("name"));
        Log.e("getScheme", uri.getScheme());
//        Log.e("getFragment", uri.getFragment());


    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

        Log.i(TAG+activity.getClass().getSimpleName(), "onActivityStarted: ");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i(TAG+activity.getClass().getSimpleName(), "onActivityResumed: ");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i(TAG+activity.getClass().getSimpleName(), "onActivityPaused: ");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i(TAG+activity.getClass().getSimpleName(), "onActivityStopped: ");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.i(TAG+activity.getClass().getSimpleName(), "onActivitySaveInstanceState: ");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.i(TAG+activity.getClass().getSimpleName(), "onActivityDestroyed: ");
    }
}
