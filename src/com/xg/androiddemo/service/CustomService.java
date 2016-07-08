package com.xg.androiddemo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.xg.androiddemo.activity.HomeFragmentActivity;

public class CustomService extends Service {

    Handler mHandler = new Handler();

	public CustomService() {
		// TODO Auto-generated constructor stub
		
		Log.e("CustomService", "CustomService");
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("CustomService", "onCreate");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.e("CustomService", "onBind");
		return new LocalBinder();
	}

	public class LocalBinder extends Binder {
		public CustomService getService() {
			// Return this instance of LocalService so clients can call public methods
			return CustomService.this;
		}
	}

	public void sayHelloWorld(){
		Toast.makeText(this.getApplicationContext(), "Hello World Local Service!", Toast.LENGTH_SHORT).show();
	}
	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("CustomService", "onDestroy");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("onStartCommand", "onStartCommand");

        this.sayHelloWorld();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                postNotification2();
            }
        },5000);

		return super.onStartCommand(intent, flags, startId);

	}

	/* (non-Javadoc)
         * @see android.app.Service#onRebind(android.content.Intent)
         */
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		Log.e("CustomService", "onRebind");
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

        Intent intent = new Intent(this, HomeFragmentActivity.class);    //点击通知进入的界面
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        notification.setLatestEventInfo(this, "开会通知", "今天下午去会议室开会", contentIntent);
		  mNotificationManager.notify(0, notification);//发送通知
	}

    private void postNotification2(){
        Intent intent = new Intent(this, HomeFragmentActivity.class);    //点击通知进入的界面
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager mNotificationManager
                = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        int icon = android.R.drawable.sym_action_email;
        long when = System.currentTimeMillis();
        Notification notification = new NotificationCompat.Builder(getApplicationContext()).setTicker("ticker").setContentTitle("title").setContentText("content-xugang").setSmallIcon(android.R.drawable.sym_action_email).setAutoCancel(true).setContentIntent(contentIntent).build();




       // notification.setLatestEventInfo(this, "开会通知", "今天下午去会议室开会", contentIntent);
        mNotificationManager.notify(0, notification);//发送通知
    }

}
