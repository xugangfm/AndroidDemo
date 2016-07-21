package com.xg.androiddemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CustomReceiver extends BroadcastReceiver {

	public final static  String action_id ="com.xg.local_receiver";
	public CustomReceiver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		String name=intent.getAction();
		Log.i("local receiver", "用静态注册的广播"+name);
		Log.i("context", context.toString());
		Log.i("onReceive", "ReceiverTestActivity, pid="+android.os.Process.myPid());
	}

}
