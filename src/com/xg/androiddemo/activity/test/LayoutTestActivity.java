package com.xg.androiddemo.activity.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.xg.androiddemo.R;
import com.xg.androiddemo.parent.BaseActivity;

public class LayoutTestActivity extends BaseActivity {

	/* (non-Javadoc)
	 * @see com.xg.androiddemo.parent.BaseActivity#onCreate(android.os.Bundle)
	 */
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test_act_layout);
		tv=(TextView) findViewById(R.id.test_layoutparam_text);
		Button button = (Button) findViewById(R.id.buttonlayout_test2);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutParams params=tv.getLayoutParams();
				int width=params.width;
				int height=params.height;
				Log.i("layoutparam", "width"+width);
				Log.i("layoutparam", "height"+height);
				Log.i("layoutparam", "MATCH_PARENT"+LayoutParams.MATCH_PARENT);
				Log.i("layoutparam", "WRAP_CONTENT"+LayoutParams.WRAP_CONTENT);
			}
		});
	}

}
