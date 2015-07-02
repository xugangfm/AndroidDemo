package com.xg.androiddemo.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xg.androiddemo.parent.BaseActivity;

public class ServiceTestActivity extends BaseActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xg.androiddemo.parent.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

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

				
			}
		});
		
		Button btn4 = new Button(this);
		btn4.setText("ubbind service");
		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent("com.xg.SERVICE_DEMO");
				
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
