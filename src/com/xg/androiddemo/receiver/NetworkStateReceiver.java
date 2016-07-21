package com.xg.androiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkStateReceiver extends BroadcastReceiver {
    String TAG = this.getClass().getSimpleName();
    private static final String INTENT_ACTION_PROXY_CHANGED = "android.intent.action.PROXY_CHANGE";
    private static final String INTENT_ACTION_NETWORK_CHANGED = "android.net.conn.CONNECTIVITY_CHANGE";

    private final static int STATUS_WIFI = 2;
    private final static int STATUS_MOBILE = 1;
    private final static int STATUS_ERROR = 0;

    private static int mStastus = STATUS_WIFI;
    public NetworkStateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (INTENT_ACTION_NETWORK_CHANGED.equals(intent.getAction())) {

            //ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

           // State state = connManager.getActiveNetworkInfo().getState();

            //Log.i(TAG, "onReceive network change , state="+state.name());
           // Log.i(TAG, "onReceive network change , pid="+android.os.Process.myPid());

           // State wifistate = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
           // Log.i(TAG, "onReceive network change , wifistate="+wifistate.name());

           // NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
           // final int networkType = networkInfo.getType();
            //final int networkSubType = networkInfo.getSubtype();

            //Log.i(TAG, "onReceive network change , networkInfo="+networkInfo.getTypeName()+" | "+networkInfo.getSubtypeName()+" | "+networkInfo.isConnected());

            int temp_status = -1;
            NetworkInfo.State wifi_state = null;
            NetworkInfo.State mobile_state = null;

            Log.i(TAG, "Receive the network change message");

            ConnectivityManager ConnManager =
                    (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

            if (ConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) == null)
                wifi_state = null;
            else
                wifi_state = ConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

            if (ConnManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) == null)
                mobile_state = null;
            else
                mobile_state = ConnManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

            if (wifi_state != null && wifi_state == NetworkInfo.State.CONNECTED) { //判断是否正在使用Wifi
                temp_status = STATUS_WIFI;
                Log.i(TAG, "mStatus is " + mStastus + ",the network has changed to be WIFI");
            } else if (mobile_state != null && mobile_state == NetworkInfo.State.CONNECTED) { //判断是否正在使用Mobile
                temp_status = STATUS_MOBILE;
                Log.i(TAG, "mStatus is " + mStastus + ",the network has changed to be MOBILE");
            } else {
                temp_status = STATUS_ERROR;
                Log.i(TAG, "mStatus is " + mStastus + ",the network has changed to be ERROR");
            }


            if (temp_status != mStastus) {  //改变后的网络和原来不一样，说明状态确实改变，则进行相应处理。
                if (temp_status == STATUS_ERROR) {
                    Log.i(TAG, "No network! !!");

                } else {
                    Log.i(TAG, "network available! Stop the heart beat!!");
                }
                Log.i(TAG, "The network type has been changed.Notify the service to re-connect");
            } else {
                Log.i(TAG, "The network is same as before, do nothing!!");
            }

            mStastus = temp_status;


        } else if (INTENT_ACTION_PROXY_CHANGED.equals(intent.getAction())) {

        }
    }
}
