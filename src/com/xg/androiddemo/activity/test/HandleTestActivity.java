package com.xg.androiddemo.activity.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.xg.androiddemo.R;
import com.xg.androiddemo.parent.BaseActivity;

public class HandleTestActivity extends BaseActivity {

	public HandleTestActivity() {
		// TODO Auto-generated constructor stub
	}

	private Handler handler=new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Bundle data=msg.getData();
			
			Bitmap bitmap =data.getParcelable("bitmap");
			
			ImageView imageView=new ImageView(HandleTestActivity.this);
			imageView.setImageBitmap(bitmap);
			
			new AlertDialog.Builder(HandleTestActivity.this)
			.setTitle("image")
			.setView(imageView)
			.setPositiveButton("OK", null)
			.show();
			
		
		}
		
		
	};
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xg.androiddemo.parent.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		ListView lv = new ListView(this);

		ArrayList<String> arrayList = new ArrayList<String>();

		arrayList.add("Download image with thread");
		arrayList.add("Download image with runnable");
		
		arrayList.add("handle post runnable");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayList);

		lv.setAdapter(adapter);
		
		setContentView(lv);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			/* (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				int index=arg2;
				
				
				if (index == 0) {
					download();
				}
				
				if(index ==1){
					download2();
				}
				
				if (index==2) {
					
					handler.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
							new AlertDialog.Builder(HandleTestActivity.this)
							.setTitle("handle post")
							.setMessage("post runnable")
							.setPositiveButton("ok", null)
							.setNegativeButton("NO", null)
							.show();
						}
					});
					
				}
				
			}
		});

	}
	
	private void download2(){
		Runnable runable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String urlString="http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";				
				//Bitmap bitmap=getBitmapFromUrl(urlString);
				Bitmap bitmap=getBitmapWithHttpClient(urlString);
				Message msg=handler.obtainMessage();
				msg.what=1;
				Bundle bundle=new Bundle();
				bundle.putParcelable("bitmap", bitmap);
				msg.setData(bundle);
				
				//handler.post(r)
				//handler.sendMessage(msg);
				msg.setTarget(handler);
				msg.sendToTarget();
			
			}
		};
		
		new Thread(runable).start();
	}
	
	private void download(){
		
		new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				String urlString="http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";				
				//Bitmap bitmap=getBitmapFromUrl(urlString);
				Bitmap bitmap=getBitmapWithHttpClient(urlString);
				Message msg=handler.obtainMessage();
				Bundle bundle=new Bundle();
				bundle.putParcelable("bitmap", bitmap);
				msg.setData(bundle);
				
				//handler.post(r)
				handler.sendMessage(msg);
				
			}
			
		}.start();
	}
	
	private  Bitmap getBitmapFromUrl(String imgUrl) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(imgUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			// conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	private Bitmap getBitmapWithHttpClient(String imgUri){
		Bitmap bitmap = null;
		
		try {
			URL url=new URL(imgUri);
			
			URI uri=url.toURI();
			HttpClient client=new DefaultHttpClient();
			
			HttpGet get=new HttpGet(uri);
			
			HttpResponse response=client.execute(get);
			InputStream is =response.getEntity().getContent();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return bitmap;
	}

}
