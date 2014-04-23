package com.xg.androiddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service {

	public DownloadService() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
