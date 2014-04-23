package com.xg.androiddemo.activity.test;

import android.R.string;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xg.androiddemo.parent.BaseActivity;
import com.xg.androiddemo.task.ImageDownloadTask;
import com.xg.androiddemo.task.TaskProcessInvoker;


public class DownloadImageActivity extends BaseActivity implements TaskProcessInvoker{

	private ImageView imageView;
	private TextView urltv;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xg.androiddemo.parent.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		TextView tv = new TextView(this);
		tv.setText(this + "");
		Button btn = new Button(this);
		
		 urltv = new TextView(this);
		
		btn.setText("download image");
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String urlString="http://img1.gtimg.com/6/617/61737/6173711_1200x1000_0.jpg";
				new ImageDownloadTask(DownloadImageActivity.this, DownloadImageActivity.this).execute(new String[]{urlString});
			urltv.setText(urlString);
			}
		});

		
		
		imageView = new ImageView(this);
		
	
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(tv);
		layout.addView(btn);
		layout.addView(urltv);
		
		layout.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);

		this.setContentView(layout);
		
		

	}

	/* (non-Javadoc)
	 * @see com.xg.androiddemo.task.TaskProcessInvoker#taskFinished(android.graphics.Bitmap)
	 */
	@Override
	public void taskFinished(Bitmap bitmap) {
		// TODO Auto-generated method stub
		imageView.setImageBitmap(bitmap);
	}
	
	

}
