package com.xg.androiddemo.activity.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xg.androiddemo.parent.BaseActivity;
import com.xg.androiddemo.service.CustomService;
import com.xg.androiddemo.service.MessegerService;

public class ServiceTestActivity extends BaseActivity {

	String TAG = this.getClass().getCanonicalName();
	ServiceConnection mSc;

	ServiceConnection remoteSC;

	CustomService customService;

	boolean isBound = false;
	Messenger mMessenger;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xg.androiddemo.parent.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mSc = new ServiceConnection(){
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.d("onServiceConnected", "service connected");
				 customService = ((CustomService.LocalBinder)service).getService();
				customService.sayHelloWorld();
			}

			@Override//这个方法意外中断才会调用
			public void onServiceDisconnected(ComponentName name) {
				Log.e("onServiceDisconnected", "service disconnected");
				customService.sayHelloWorld();
			}
		};

		remoteSC = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				isBound = true;

				// Create the Messenger object
				mMessenger = new Messenger(service);

				Log.i(TAG, mMessenger.toString());

				// Create a Message
				// Note the usage of MSG_SAY_HELLO as the what value
				Message msg = Message.obtain(null, MessegerService.MSG_SAY_HELLO, 0, 0);

				// Create a bundle with the data
				Bundle bundle = new Bundle();
				bundle.putString("hello", "world");

				// Set the bundle data to the Message
				msg.setData(bundle);

				// Send the Message to the Service (in another process)
				try {
					mMessenger.send(msg);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				mMessenger = null;
				isBound = false;
			}
		};




		Button btn = new Button(this);
		btn.setText("start service");
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent("com.xg.SERVICE_DEMO");
				startService(it);
			}
		});

		Button btn2 = new Button(this);
		btn2.setText("stop service");
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent("com.xg.SERVICE_DEMO");
				stopService(it);
			}
		});
		
		
		
		Button btn3 = new Button(this);
		btn3.setText("bind service");
		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent("com.xg.SERVICE_DEMO");
				bindService(it,mSc, Context.BIND_AUTO_CREATE);
				
			}
		});
		
		Button btn4 = new Button(this);
		btn4.setText("ubbind service");
		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent("com.xg.SERVICE_DEMO");
				unbindService(mSc);
			}
		});

		Button btn5 = new Button(this);
		btn5.setText("bind remote service");
		btn5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ServiceTestActivity.this,MessegerService.class);
				bindService(it,remoteSC,Context.BIND_AUTO_CREATE);
			}
		});


		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		layout.addView(btn);
		layout.addView(btn2);
		layout.addView(btn3);
		layout.addView(btn4);
		layout.addView(btn5);
		setContentView(layout);

	}

}
