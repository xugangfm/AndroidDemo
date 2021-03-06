package com.xg.androiddemo.activity.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xg.androiddemo.R;

public class CCActivity extends FragmentActivity {
    String TAG = "CCActivity";

    @Override
    protected void onDestroy() {
        Log.i(TAG+"task id:"+getTaskId(), "onDestroy: "+this.toString());
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG+"task id:"+getTaskId(), "onStart: "+this.toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc);
        Log.i(TAG+"task id:"+getTaskId(), "onCreate: "+this.toString());
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button buttonaa = (Button) findViewById(R.id.startaa);
        buttonaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CCActivity.this, AAActivity.class);
                startActivity(intent);
            }
        });

        Button buttonbb = (Button) findViewById(R.id.startbb);
        buttonbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CCActivity.this,BBActivity.class);
                startActivity(intent);
            }
        });

        Button buttoncc = (Button) findViewById(R.id.startcc);
        buttoncc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CCActivity.this,CCActivity.class);
                startActivity(intent);
            }
        });
    }

}
