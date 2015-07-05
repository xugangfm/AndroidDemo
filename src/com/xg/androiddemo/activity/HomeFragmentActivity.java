package com.xg.androiddemo.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.xg.androiddemo.R;
import com.xg.androiddemo.activity.test.DownloadImageActivity;
import com.xg.androiddemo.activity.test.HandleTestActivity;
import com.xg.androiddemo.activity.test.LayoutTestActivity;
import com.xg.androiddemo.activity.test.ListViewDemoActivity;
import com.xg.androiddemo.activity.test.ReceiverTestActivity;
import com.xg.androiddemo.activity.test.ServiceTestActivity;
import com.xg.androiddemo.parent.BaseActivity;

//import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.FragmentActivity;

public class HomeFragmentActivity extends BaseActivity implements TabFirstFragment.TabFirstFragmentClickListener {

	private FragmentManager fragmentManager;  
	private int selected_index=0;
	private LinearLayout contentView;
	
	public static class StaticReceiver  extends BroadcastReceiver{

		/* (non-Javadoc)
		 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			Log.i("StaticReceiver", "静态内部类广播");
		}
		
		
	}

	public  void onclicked(int resId,int index){

		if (resId == R.id.first_act_button1){
			Bundle data = new Bundle();
			pushActivity(HomeFragmentActivity.this, data,
					ListViewDemoActivity.class);
		}

		if (resId == R.id.first_act_button2){
			Bundle data = new Bundle();
			pushActivity(HomeFragmentActivity.this, null,
					LayoutTestActivity.class);
		}

		if (resId == R.id.first_act_listview){

			if (index==0) {
				pushActivity(HomeFragmentActivity.this, null, DownloadImageActivity.class);
			}
			if (index==1) {
				pushActivity(HomeFragmentActivity.this, null, ServiceTestActivity.class);
			}

			if (index==2) {
				//postNotification();
			}

			if (index==3) {
				pushActivity(HomeFragmentActivity.this, null, ReceiverTestActivity.class);
			}

			if (index==4) {
				pushActivity(HomeFragmentActivity.this, null, HandleTestActivity.class);
			}
		}


	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 fragmentManager = getSupportFragmentManager();
		
		Log.v("main task id", ":"+getTaskId());
		
		
		
		setContentView(R.layout.home_act_layout);
		contentView = (LinearLayout) findViewById(R.id.home_content_view);
		intTabBar();
		switchActivity(selected_index);
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_act_layout, menu);
		return true;
	}

	private void intTabBar() {
		ArrayList<HashMap<String, Object>> menu_data = new ArrayList<HashMap<String, Object>>();
		int[] images = { R.drawable.menu_first_pressed,
				R.drawable.menu_second_pressed, R.drawable.menu_third_pressed,
				R.drawable.menu_forth_pressed };
		String[] menu_texts = { "程序构架", "视图", "Third", "Fouth" };

		for (int i = 0; i < images.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("menu_image", images[i]);
			map.put("menu_text", menu_texts[i]);
			menu_data.add(map);
		}
		
		GridView gv = (GridView) findViewById(R.id.home_grid_view);
		SimpleAdapter adapter = new SimpleAdapter(this, menu_data,
				R.layout.tab_grid_item, new String[] { "menu_image", "menu_text" },
				new int[] { R.id.tab_grid_item_iamge, R.id.tab_grid_item_text });
		gv.setAdapter(adapter);
		
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (selected_index == position) {
					return;
				}
				selected_index = position;
				switchActivity(selected_index);
			}
		});
		
		
	}
	
	private void switchActivity(int selected) {
		contentView.removeAllViews();
		Intent intent = null;
		String tag = "";
		if (selected == 0) {
			TabFirstFragment temp = new TabFirstFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.home_content_view, temp);
			transaction.commit();
		} else if (selected == 1) {
			TabSecondFragment temp = new TabSecondFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.home_content_view, temp);
			transaction.commit();
			
		} else if (selected == 2) {
			
		} else if (selected == 3) {
			
		}
//		Window subActivity = getLocalActivityManager().startActivity(tag,
//				intent);
//		
//		//��ȡView
//		View subActivityView=subActivity.getDecorView();
//		// �������View
//	
//		contentView.addView(subActivityView,
//				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
	}
	
	


}
