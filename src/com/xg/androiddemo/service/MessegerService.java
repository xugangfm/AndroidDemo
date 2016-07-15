package com.xg.androiddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MessegerService extends Service {

    private String TAG = "MessengerService";

   public static final int MSG_SAY_HELLO = 1;


    Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Bundle bundle = msg.getData();
                    String hello = (String) bundle.get("hello");

                    Toast.makeText(getApplicationContext(), hello, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };


    Messenger mMessenger = new Messenger(mHander);

    public MessegerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate called");


    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind done");
        Log.i(TAG, mMessenger.toString());
        return mMessenger.getBinder();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }
}
