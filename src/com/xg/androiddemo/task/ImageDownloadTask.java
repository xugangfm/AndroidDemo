package com.xg.androiddemo.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class ImageDownloadTask extends AsyncTask<String, Integer, Bitmap> {

	Activity activity;
	TaskProcessInvoker invoker;
	public ImageDownloadTask(Activity act ,TaskProcessInvoker invoker) {
		// TODO Auto-generated constructor stub
		Log.i("xugang", "CustomTask");
		this.activity=act;
		this.invoker=invoker;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.i("xugang", "doInBackground");
		String urlString=params[0];
		
		InputStream is=null;
		Bitmap bitmap=null;
		try {
			URL url=new URL(urlString);
			Log.v("image url", url.getPath());
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.connect();
			 is=connection.getInputStream();
			//resultsString=connection.getResponseMessage();
			//InputStream is = new ByteArrayInputStream(text.getBytes("UTF-8"));
			//resultsString=convertStreamToString(is);
			 bitmap = BitmapFactory.decodeStream(is);
			 is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("network", "connect fail");
		}
		
		return bitmap;
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.i("xugang", "onPostExecute");
		//Log.i("xugang-msg", result);
		invoker.taskFinished(result);
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		Log.i("xugang", "onPreExecute");
	}
	
	
	
	
	public String convertStreamToString(InputStream is) {    
        /* 
          * To convert the InputStream to String we use the BufferedReader.readLine() 
          * method. We iterate until the BufferedReader return null which means 
          * there's no more data to read. Each line will appended to a StringBuilder 
          * and returned as String. 
          */   
		StringBuilder sb = new StringBuilder(); 
        try {    
        	
        	BufferedReader  reader = new BufferedReader(new InputStreamReader(is,"gb2312"));    
               
      
            String line = null; 
        	
        	
            while ((line = reader.readLine()) != null) {    
                 sb.append(line + "\n");    
             }    
         } catch (IOException e) {    
             e.printStackTrace();    
         } finally {    
            try {    
                 is.close();    
             } catch (IOException e) {    
                 e.printStackTrace();    
             }    
         }    
   
        return sb.toString();    
     }    

}
