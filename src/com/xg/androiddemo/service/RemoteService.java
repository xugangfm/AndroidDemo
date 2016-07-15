package com.xg.androiddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import com.xg.androiddemo.IGroupInfoCallbackInterface;
import com.xg.androiddemo.IHandlerAidlInterface;
import com.xg.androiddemo.db.GroupInfo;
import com.xg.androiddemo.db.UserInfo;

public class RemoteService extends Service {
    private final String TAG = RemoteService.class.getSimpleName();
    public RemoteService() {
    }


    IHandlerAidlInterface.Stub mBinder = new IHandlerAidlInterface.Stub() {
        @Override
        public String getUserInfo(UserInfo userinfo) throws RemoteException {
            return userinfo.user_id+userinfo.user_name+userinfo.extra_info;
        }

        @Override
        public void getGroupInfo(final IGroupInfoCallbackInterface callback) throws RemoteException {

            final GroupInfo groupInfo = new GroupInfo();
            groupInfo.extra_info= "groupinfo extra";
            groupInfo.user_id = "life group";

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        callback.onSuccess(groupInfo);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }

        @Override
        public int add(int x, int y) throws RemoteException {
            return x+y;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate, pid="+android.os.Process.myPid());
        Log.i(TAG, "onCreate, threadid="+Thread.currentThread().getId());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.i(TAG, "onBind, pid="+android.os.Process.myPid());
        return mBinder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind, pid="+android.os.Process.myPid());

        return false;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy, pid=" + android.os.Process.myPid());
        Process.killProcess(android.os.Process.myPid());
    }
}
