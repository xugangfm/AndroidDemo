package com.xg.androiddemo.activity.test2;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xg.androiddemo.parent.BaseActivity;

public class ListViewHFActivity  extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ListView listView=new ListView(this);
		
		ArrayList<String> arrayList = new ArrayList<String>();
		for(int i=0;i<10;i++){
			arrayList.add("test gridView EditText");
			arrayList.add("test scroll layout");
			
			arrayList.add("scrollview contain child ");
			arrayList.add("list view header footer");
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayList);

		TextView headerTextView=new TextView(this);
		headerTextView.setText("header头部");
		headerTextView.setTextSize(50);
		listView.addHeaderView(headerTextView);
		
		listView.setAdapter(adapter);
		
		
		
		TextView footertextView =new TextView(this);
		footertextView.setText("尾部");
		footertextView.setTextSize(50);
		listView.addFooterView(footertextView);
		
		
		EditText editText=new EditText(this);
		listView.addFooterView(editText);
		
		
		setContentView(listView);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i("list view index", "index:"+arg2);
			}
			
		});
		
	}
	
	
	

}
