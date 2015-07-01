package com.xg.androiddemo.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.*;


import com.xg.androiddemo.adapter.CustomListviewAdapter;
import com.xg.androiddemo.event.SubMenuOnClickListener;
import com.xg.androiddemo.parent.BaseActivity;



public class ListViewDemoActivity extends BaseActivity implements SubMenuOnClickListener{
	
	private ListView listView;
	private CustomListviewAdapter adapter;
	private List<HashMap<String,Object>> data_list;
	
	
	
	/* (non-Javadoc)
	 * @see com.xg.androiddemo.parent.BaseActivity#onNewIntent(android.content.Intent)
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.v("ListViewDemoActivity", "onNewIntent");
	}

	/* (non-Javadoc)
	 * @see com.xg.androiddemo.parent.BaseActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("ListViewDemoActivity", "onResume");
	}

	/* (non-Javadoc)
	 * @see com.xg.androiddemo.parent.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("ListViewDemoActivity", "onCreate");
		
		listView=new ListView(this);
		data_list=createDataList();
		adapter=new CustomListviewAdapter(this, this,data_list);
		listView.setAdapter(adapter);
		
		this.setContentView(listView);
		
		listView.setOnItemClickListener(new OnItemClickListener(){
			
			  @Override  
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
	                    long arg3) {
				  int index=arg2;
				  
				  HashMap<String,Object> map=data_list.get(index);
				  Boolean flag=(Boolean) map.get("open_flag");
				  
				  if (flag) {
					  map.put("open_flag", false);
				}else {
					
					for(int i=0;i<data_list.size();i++)
					{
						HashMap<String,Object> inner_map=data_list.get(i);
						inner_map.put("open_flag", false);
					}			
					map.put("open_flag", true);
				}
				  
				 adapter.notifyDataSetChanged();
				 
				  setTitle("demo"+index+"4231423");
	            }
		
		}
		);
		
		
	}
	
	private List<HashMap<String,Object>> createDataList()
	{
		List<HashMap<String,Object>> array=new ArrayList<HashMap<String,Object>>();
		
		for(int i=0;i<10;i++)
		{
			HashMap<String,Object> dict=new HashMap<String,Object>();
			dict.put("title", "joiwjfoiew");
			String number=String.format("%1$,09d", i);
			dict.put("detail", number);
			//dict.put("image",R.drawable.book_icon);
			dict.put("open_flag", false);
			array.add(dict);
			

		}
		
		return array;
	}

	/* (non-Javadoc)
	 * @see com.xg.androiddemo.event.SubMenuOnClickListener#viewClicked(android.view.View, int)
	 */
	@Override
	public void viewClicked(View view, int index) {
		// TODO Auto-generated method stub
		Bundle dataBundle=new Bundle();
		dataBundle.putInt("data", index);
		pushActivity(this, dataBundle, DataPassActivity.class);
		
	}
	
	

}
