package com.xg.androiddemo.activity;

import android.app.ActivityGroup;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.xg.androiddemo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends ActivityGroup {

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

            //HomeActivity.this.switchActivity(3);

		}
		
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
			intent = new Intent(HomeActivity.this, TabFirstActivity.class);
			tag = TabFirstActivity.class.getSimpleName();
		} else if (selected == 1) {
			intent = new Intent(HomeActivity.this, TabSecondActivity.class);
			tag = TabSecondActivity.class.getSimpleName();
		} else if (selected == 2) {
			intent = new Intent(HomeActivity.this, TabThirdActivity.class);
			tag = TabThirdActivity.class.getSimpleName();
		} else if (selected == 3) {
			intent = new Intent(HomeActivity.this, TabFouthActivity.class);
			tag = TabFouthActivity.class.getSimpleName();
		}
		Window subActivity = getLocalActivityManager().startActivity(tag,
				intent);
		
		//��ȡView
		View subActivityView=subActivity.getDecorView();
		// �������View
	
		contentView.addView(subActivityView,
				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
	}
	
	

}
