package com.xg.androiddemo.activity.test;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.xg.androiddemo.R;

public class ListActionBarTestActivity extends ActionBarActivity implements ActionBar.OnNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_action_bar_test);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false); // 决定左上角图标的右侧是否有向左的小箭头, true
//        // 有小箭头，并且图标可以点击
        actionBar.setDisplayShowHomeEnabled(true);//is show home icon

        actionBar.setTitle("custom Title");
        actionBar.setIcon(R.drawable.de_title_back);

        String[] str = { "1", "2", "3", "4", "5" };
        actionBar.setTitle("列表菜单");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(new ArrayAdapter<String>(
                        ListActionBarTestActivity.this,
                        android.R.layout.simple_spinner_item, str),
                ListActionBarTestActivity.this);

    }

    @Override
    public boolean onNavigationItemSelected(int i, long l) {


        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_action_bar_test, menu);
        return true;
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
            Log.e("home back", "YES");
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
