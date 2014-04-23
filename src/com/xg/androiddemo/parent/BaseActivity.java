package com.xg.androiddemo.parent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onNewIntent(android.content.Intent)
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	protected void pushActivity(Activity currentActivity,Bundle bundle,Class willActivityClass){
		Intent intent=new Intent(currentActivity, willActivityClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		if (null==bundle) {
			bundle=new Bundle();
		}
		intent.putExtras(bundle);
		currentActivity.startActivity(intent);
		
	}
	
	protected void popActivity(Activity currentActivity){
		currentActivity.finish();
		
	}
	
	protected void popToActivity(Activity currentActivity,Class willActivityClass){
		Intent intent=new Intent(currentActivity, willActivityClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		currentActivity.startActivity(intent);
		
	}

}
