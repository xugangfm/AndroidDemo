package com.xg.androiddemo.activity.test2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xg.androiddemo.R;
import com.xg.androiddemo.parent.BaseActivity;

public class ScrollViewActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_act_layout);
		
		//LinearLayout layout=new LinearLayout(this);
		
		LinearLayout layout=(LinearLayout) findViewById(R.id.scroll_content_view);
		
		//layout.setBackgroundColor(Color.parseColor("#00FFFA"));
		
		for(int i=0;i<19;i++)
		{
		
			KeyValueItem item=new KeyValueItem(this);
			item.setKey("key"+i);
			item.setValue("value"+i);
			item.setId(i+100);
			layout.addView(item,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		}
		
		EditText editText=new EditText(this);
		
		layout.addView(editText,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		Button button=new Button(this);
		button.setText("press");
		
		layout.addView(button,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		//把需要滚动的布局放到scrollview中
		//ScrollView scrollView=(ScrollView) findViewById(R.id.scroll_view);
		//scrollView.addView(layout);
		//scrollView.addView(layout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	}
	
	
	
	
	

}

 class KeyValueItem  extends LinearLayout{

	 private TextView key;
	 private TextView value;
	 
	public KeyValueItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.key_value_item, this);
		
		key=(TextView) findViewById(R.id.key_text_view);
		value=(TextView) findViewById(R.id.value_text_view);
	}

	public KeyValueItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.key_value_item, this);
		key=(TextView) findViewById(R.id.key_text_view);
		value=(TextView) findViewById(R.id.value_text_view);
	}

	
	public void setKey(String key) {
		this.key.setText(key);
	}



	public void setValue(String value) {
		this.value.setText(value);
	}
	
}
