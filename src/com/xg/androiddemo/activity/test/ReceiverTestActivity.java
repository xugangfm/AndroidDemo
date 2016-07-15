package com.xg.androiddemo.activity.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xg.androiddemo.parent.BaseActivity;

public class ReceiverTestActivity extends BaseActivity {

	/* (non-Javadoc)
	 * @see com.xg.androiddemo.parent.BaseActivity#onCreate(android.os.Bundle)
	 */
	private  BroadcastReceiver receiver=new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String name=intent.getAction();
			Log.i("receiver", "动态注册广播:"+name);
			 
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		registerBoradcastReceiver();
		
		Button btn = new Button(this);
		btn.setText("send broardcast 静态注册广播");
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent("com.xg.receiver_demo");
				sendBroadcast(it);
				Log.i("ReceiverTestActivity", "finish send");
			}
		});

        Button btn3 = new Button(this);
        btn3.setText("send broardcast 动态注册广播");
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent("com.xg.receiver_dy_demo");
                sendBroadcast(it);
                Log.i("ReceiverTestActivity", "finish send dy 广播");
            }
        });



        Button btn2 = new Button(this);
		btn2.setText("send broardcast 静态内部类");
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent("com.xg.static_receiver_demo");
				sendBroadcast(it);
				Log.i("ReceiverTestActivity", "finish send local 广播");
			}
		});
		
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(btn);
        layout.addView(btn3);
		layout.addView(btn2);
		setContentView(layout);
	}
	
	
	private  void registerBoradcastReceiver(){ 
        IntentFilter myIntentFilter = new IntentFilter(); 
        myIntentFilter.addAction("com.xg.receiver_dy_demo");
        //注册广播       
        registerReceiver(receiver, myIntentFilter); 
       
    } 

	
}
