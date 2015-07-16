package com.xg.androiddemo.parent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class BaseActivity extends ActionBarActivity {

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

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public void pushActivity(Activity currentActivity,Bundle bundle,Class willActivityClass){
		Intent intent=new Intent(currentActivity, willActivityClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		if (null==bundle) {
			bundle=new Bundle();
		}
		intent.putExtras(bundle);
		currentActivity.startActivity(intent);
		
	}

	public void popActivity(Activity currentActivity){
		currentActivity.finish();
		
	}

	public void popToActivity(Activity currentActivity,Class willActivityClass){
		Intent intent=new Intent(currentActivity, willActivityClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		currentActivity.startActivity(intent);
		
	}

}
