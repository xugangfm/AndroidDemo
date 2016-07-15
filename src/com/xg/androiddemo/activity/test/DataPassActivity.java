package com.xg.androiddemo.activity.test;

import com.xg.androiddemo.R;
import com.xg.androiddemo.activity.HomeActivity;
import com.xg.androiddemo.parent.BaseActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DataPassActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_pass);

		Bundle dataBundle = getIntent().getExtras();
		int index = dataBundle.getInt("data");

		setTitle("parent index : " + index);

		Button btnButton1 = (Button) findViewById(R.id.button_pop_self);
		Button btnButton2 = (Button) findViewById(R.id.button_pop_to_home);

		btnButton1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				popActivity(DataPassActivity.this);

			}
		});

		btnButton2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popToActivity(DataPassActivity.this, HomeActivity.class);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_data_pass, menu);
		return true;
	}

}
