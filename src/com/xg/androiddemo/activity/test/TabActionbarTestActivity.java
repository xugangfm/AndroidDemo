package com.xg.androiddemo.activity.test;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.xg.androiddemo.R;

public class TabActionbarTestActivity extends ActionBarActivity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar_test);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
       actionBar.setDisplayHomeAsUpEnabled(false); // 决定左上角图标的右侧是否有向左的小箭头, true
//        // 有小箭头，并且图标可以点击
        actionBar.setDisplayShowHomeEnabled(true);//is show home icon

        actionBar.setTitle("custom Title");
        actionBar.setIcon(R.drawable.de_title_back);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Tab1")
                .setTabListener(TabActionbarTestActivity.this));
        actionBar.addTab(actionBar.newTab().setText("Tab2")
                .setTabListener(TabActionbarTestActivity.this));
        actionBar.addTab(actionBar.newTab().setText("Tab3")
                .setTabListener(TabActionbarTestActivity.this));
        actionBar.addTab(actionBar.newTab().setText("Tab11")
                .setTabListener(TabActionbarTestActivity.this));
        actionBar.addTab(actionBar.newTab().setText("Tab21")
                .setTabListener(TabActionbarTestActivity.this));
        actionBar.addTab(actionBar.newTab().setText("Tab31")
                .setTabListener(TabActionbarTestActivity.this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar_test, menu);
        return true;
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home){
            Log.e("home back","YES");
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
