package com.xg.androiddemo.Utils;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by xugang on 16/7/14.
 */
public class XGWakeLock {
    PowerManager.WakeLock pmLock;
    Context mContext ;

    public XGWakeLock(Context context){this.mContext = context;}

    public  void acquireWakeLock(int timeout){
        acquireWakeLock(timeout,PowerManager.PARTIAL_WAKE_LOCK);
    }

    protected void acquireWakeLock(int timeout,int level){
        if (null == pmLock){
            PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            pmLock =pm.newWakeLock(level, this.getClass().getCanonicalName());
            if(timeout <= 0){
                pmLock.acquire();
            }
            else {
                pmLock.acquire(timeout);
            }
        }
    }

    public void releaseWakeLock(){
        if (null !=pmLock && pmLock.isHeld()){
            pmLock.release();
            pmLock = null;
        }
    }



}
