package com.xg.androiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HeartbeatReceiver extends BroadcastReceiver {
    public HeartbeatReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e("HeartbeatReceiver", "onReceive: ");

        WakeLockUtils.startNextHeartbeat(context);
    }
}
