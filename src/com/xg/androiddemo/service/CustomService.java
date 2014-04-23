package com.xg.androiddemo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CustomService extends Service {

	public CustomService() {
		// TODO Auto-generated constructor stub
		
		Log.i("CustomService", "CustomService");
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("CustomService", "onCreate");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i("CustomService", "onBind");
		return null;
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("CustomService", "onDestroy");
		super.onDestroy();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onRebind(android.content.Intent)
	 */
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("CustomService", "onRebind");
		super.onRebind(intent);
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i("CustomService", "onStart");
		
		//postNotification();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onUnbind(android.content.Intent)
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("CustomService", "onUnbind");
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	private void postNotification(){
		NotificationManager mNotificationManager
		= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		  int icon = android.R.drawable.sym_action_email;
		  long when = System.currentTimeMillis();
		  Notification notification = new Notification(icon,"开会",when);
		 
		  PendingIntent contentIntent = PendingIntent.getService(this, 0, null, 0);
		  notification.setLatestEventInfo(this, "开会通知", "今天下午去会议室开会", contentIntent);
		  mNotificationManager.notify(0, notification);//发送通知
	}

}
