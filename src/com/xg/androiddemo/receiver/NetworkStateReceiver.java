package com.xg.androiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetworkStateReceiver extends BroadcastReceiver {
    String TAG = this.getClass().getSimpleName();
    private static final String INTENT_ACTION_PROXY_CHANGED = "android.intent.action.PROXY_CHANGE";
    private static final String INTENT_ACTION_NETWORK_CHANGED = "android.net.conn.CONNECTIVITY_CHANGE";
    public NetworkStateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (INTENT_ACTION_NETWORK_CHANGED.equals(intent.getAction())) {
            Log.i(TAG, "onReceive network change , pid="+android.os.Process.myPid());

        } else if (INTENT_ACTION_PROXY_CHANGED.equals(intent.getAction())) {

        }
    }
}
