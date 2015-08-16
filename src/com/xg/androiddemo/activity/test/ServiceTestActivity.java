package com.xg.androiddemo.activity.test;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xg.androiddemo.parent.BaseActivity;
import com.xg.androiddemo.service.CustomService;
import com.xg.androiddemo.service.CustomService.LocalBinder;
import android.content.ComponentName;
import android.os.IBinder;

public class ServiceTestActivity extends BaseActivity {

	ServiceConnection mSc;

	CustomService customService;


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


		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		layout.addView(btn);
		layout.addView(btn2);
		layout.addView(btn3);
		layout.addView(btn4);
		setContentView(layout);

	}

}
