package com.xg.androiddemo.activity.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xg.androiddemo.R;
import com.xg.androiddemo.parent.BaseActivity;

public class ViewTestActivity extends BaseActivity {
	private GridView gv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_test_act_layout);
		gv=(GridView) findViewById(R.id.test_view_gridview);
		
		
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		for(int i=0;i<46;i++){
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("name", "App"+i);
			list.add(map);
		}
		
		
		imageAdapter adapter=new imageAdapter(this, list);
		
		gv.setAdapter(adapter);
		
		
	}
	
	
	

}

class imageAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> mList;
	private Context mContext;

	public imageAdapter(Context ctx, List<HashMap<String, Object>> list) {
		// TODO Auto-generated constructor stub
		mContext = ctx;
		mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mList == null) {
			return 0;
		} else {
			return mList.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = new ImageItemView(mContext);
		}
		
		ImageItemView item=(ImageItemView) convertView; 
		item.setText((String)mList.get(position).get("name"));
		item.setImage(R.drawable.book_icon);
		
		return convertView;
	}

}

class ImageItemView extends LinearLayout {

	private ImageView image;
	private TextView Text;

	public void setImage(int resId) {
		this.image.setImageResource(resId);
	}

	public void setText(String text) {
		Text.setText(text);
	}

	public ImageItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.image_view_item, this);

		image = (ImageView) this.findViewById(R.id.image_item_iamge);
		Text = (TextView) this.findViewById(R.id.image_item_text);
	}

	public ImageItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.image_view_item, this);

		image = (ImageView) this.findViewById(R.id.image_item_iamge);
		Text = (TextView) this.findViewById(R.id.image_item_text);
	}

}