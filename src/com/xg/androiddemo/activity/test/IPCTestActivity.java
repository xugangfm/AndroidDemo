package com.xg.androiddemo.activity.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xg.androiddemo.IGroupInfoCallbackInterface;
import com.xg.androiddemo.IHandlerAidlInterface;
import com.xg.androiddemo.db.GroupInfo;
import com.xg.androiddemo.db.UserInfo;
import com.xg.androiddemo.service.RemoteService;

import java.util.UUID;

public class IPCTestActivity extends ActionBarActivity {
    String TAG = this.getClass().getCanonicalName();
   // ServiceConnection mSc;

    ServiceConnection remoteSC;

    IHandlerAidlInterface iHandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ipctest);

        remoteSC = new ServiceConnection(){
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iHandler = IHandlerAidlInterface.Stub.asInterface(service);
                Log.i(TAG, "onServiceConnected: ");

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected: ");
            }
        };

        Button btn = new Button(this);
        btn.setText("start remote service");
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(IPCTestActivity.this,RemoteService.class);
                bindService(it,remoteSC, Context.BIND_AUTO_CREATE);
            }
        });

        Button btn1 = new Button(this);
        btn1.setText("call remote service");
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    int sum =iHandler.add(5,6);
                    Log.i(TAG+" remote call", sum+"");
                    UUID uuid = UUID.randomUUID();
                    UserInfo userInfo = new UserInfo();
                    userInfo.user_id = "10-"+uuid;
                    userInfo.user_name = "xugang";
                    userInfo.extra_info = "just do it";
                    String oo = iHandler.getUserInfo(userInfo);

                    Log.i(TAG+" remote call", oo);

                    iHandler.getGroupInfo(new IGroupInfoCallbackInterface.Stub(){
                        @Override
                        public void onSuccess(GroupInfo groupinfo) throws RemoteException {
                            Log.i(TAG, "getGroupInfo onSuccess: "+groupinfo.user_id+groupinfo.user_name+groupinfo.extra_info);
                        }
                    });



                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(btn);
      layout.addView(btn1);
//        layout.addView(btn3);
//        layout.addView(btn4);
//        layout.addView(btn5);
        setContentView(layout);

    }



}
