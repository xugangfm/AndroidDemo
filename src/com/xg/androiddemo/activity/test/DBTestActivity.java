package com.xg.androiddemo.activity.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.xg.androiddemo.R;
import com.xg.androiddemo.db.UserInfo;
import com.xg.androiddemo.db.XGDBManager;

import java.util.UUID;

public class DBTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);

        XGDBManager manager = XGDBManager.getInstance();

        UUID uuid = UUID.randomUUID();
        UserInfo userInfo = new UserInfo();
        userInfo.user_id = "10-"+uuid;
        userInfo.user_name = "xugang";
        userInfo.extra_info = "just do it";

        manager.insert(userInfo);



        manager.query(321);



    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
