package com.xg.androiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.net.NetworkInfo.State;
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

            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

           // State state = connManager.getActiveNetworkInfo().getState();

            //Log.i(TAG, "onReceive network change , state="+state.name());
           // Log.i(TAG, "onReceive network change , pid="+android.os.Process.myPid());

            State wifistate = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
           // Log.i(TAG, "onReceive network change , wifistate="+wifistate.name());

            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            final int networkType = networkInfo.getType();
            final int networkSubType = networkInfo.getSubtype();

            Log.i(TAG, "onReceive network change , networkInfo="+networkInfo.getTypeName()+" | "+networkInfo.getSubtypeName()+" | "+networkInfo.isConnected());

        } else if (INTENT_ACTION_PROXY_CHANGED.equals(intent.getAction())) {

        }
    }
}
