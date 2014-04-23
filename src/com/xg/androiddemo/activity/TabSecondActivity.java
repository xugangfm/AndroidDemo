package com.xg.androiddemo.activity;

import java.util.ArrayList;

import com.xg.androiddemo.R;
import com.xg.androiddemo.activity.test2.ListViewHFActivity;
import com.xg.androiddemo.activity.test2.ScrollLayoutActivity;
import com.xg.androiddemo.activity.test2.ScrollViewActivity;
import com.xg.androiddemo.activity.test2.ViewTestActivity;
import com.xg.androiddemo.parent.BaseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TabSecondActivity extends BaseActivity {

	private TabSecondActivity mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_second_layout);
		mContext=this;
		ListView lv = (ListView) findViewById(R.id.second_act_listview);

		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("test gridView EditText ScrollView");
		arrayList.add("test scroll layout");
		
		arrayList.add("scrollview contain child view");
		arrayList.add("list view header footer");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayList);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-genera
				int index=arg2;
				
				if (0==index) {
					pushActivity(mContext, null, ViewTestActivity.class);
				}
				if (1==index) {
					pushActivity(mContext, null, ScrollLayoutActivity.class);
				}
				if(2==index){
					pushActivity(mContext, null, ScrollViewActivity.class);
				}
				if(3==index){
					pushActivity(mContext, null, ListViewHFActivity.class);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tab_second_layout, menu);
		return true;
	}

}
