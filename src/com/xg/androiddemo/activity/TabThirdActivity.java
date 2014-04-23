package com.xg.androiddemo.activity;

import com.xg.androiddemo.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TabThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_third_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tab_third_layout, menu);
		return true;
	}

}
