package com.xg.androiddemo.activity;

import java.util.ArrayList;

import com.xg.androiddemo.R;
import com.xg.androiddemo.activity.test.DownloadImageActivity;
import com.xg.androiddemo.activity.test.HandleTestActivity;
import com.xg.androiddemo.activity.test.LayoutTestActivity;
import com.xg.androiddemo.activity.test.ListViewDemoActivity;
import com.xg.androiddemo.activity.test.ReceiverTestActivity;
import com.xg.androiddemo.activity.test.ServiceTestActivity;
import com.xg.androiddemo.parent.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TabFirstActivity extends BaseActivity {

	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_first_layout);
		initmore();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tab_first_layout, menu);
		return true;
	}

	private void initmore() {

		button1 = (Button) this.findViewById(R.id.first_act_button1);

		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle data = new Bundle();
				pushActivity(TabFirstActivity.this, data,
						ListViewDemoActivity.class);

			}
		});

		button2 = (Button) this.findViewById(R.id.first_act_button2);

		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle data = new Bundle();
				pushActivity(TabFirstActivity.this, null,
						LayoutTestActivity.class);

			}
		});
		
		ListView lv=(ListView) findViewById(R.id.first_act_listview);
		
		ArrayList<String> arrayList= new ArrayList<String>();
		
		arrayList.add("Download image activity");
		arrayList.add("try service activity");
		arrayList.add("post notification");
		arrayList.add("try broardcast activity");
		arrayList.add("try handle with thread");
		
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
		
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				int index=arg2;
				Log.v("list demo index", index+"");
				if (index==0) {
					pushActivity(TabFirstActivity.this, null, DownloadImageActivity.class);
				}
				if (index==1) {
					pushActivity(TabFirstActivity.this, null, ServiceTestActivity.class);
				}
				
				if (index==2) {
					//postNotification();
				}
				
				if (index==3) {
					pushActivity(TabFirstActivity.this, null, ReceiverTestActivity.class);
				}
				
				if (index==4) {
					pushActivity(TabFirstActivity.this, null, HandleTestActivity.class);
				}
				
				
				
				
			}
		});
		

	}
	
	private void postNotification(){
		NotificationManager mNotificationManager
		= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		  int icon = android.R.drawable.sym_action_email;
		  long when = System.currentTimeMillis();
		  Notification notification = new Notification(icon,"开会",when);
		 
		  PendingIntent contentIntent = PendingIntent.getActivity(TabFirstActivity.this, 0, null, 0);
		  notification.setLatestEventInfo(TabFirstActivity.this, "开会通知", "今天下午去会议室开会", contentIntent);
		  mNotificationManager.notify(0, notification);//发送通知
	}

}
