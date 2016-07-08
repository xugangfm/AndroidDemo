package com.xg.androiddemo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xg.androiddemo.Global;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xugang on 16/7/8.
 */
public class XGDBManager {
    private ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    private  volatile  static  XGDBManager dbManager = null;

    DBHelper mdbHelper = new DBHelper(Global.getAppContext());

    public static  XGDBManager getInstance(){

        if(null == dbManager){

            synchronized (XGDBManager.class){
                dbManager = new XGDBManager();

            }

        }
        return  dbManager;
    }

    public  void insert(final UserInfo userInfo){

        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db;

                db = mdbHelper.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(mdbHelper.COLUMN_USER_ID,userInfo.user_id);
                cv.put(mdbHelper.COLUMN_USER_NAME,userInfo.user_name);
                cv.put(mdbHelper.COLUMN_EXTRA_INFO,userInfo.extra_info);

                db.insert(mdbHelper.DB_TABLE,null,cv);
            }
        });





    }

    public  void del(UserInfo userInfo){

    }
    public void update(UserInfo userInfo){

    }

    public Cursor query(int user_id){


        SQLiteDatabase db;

        db = mdbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("select * from user_info where user_name =  ?", new String[]{"xugang"});

        while(c.moveToNext()){
            String _id = c.getString(c.getColumnIndex(mdbHelper.COLUMN_USER_ID));
            String name = c.getString(c.getColumnIndex(mdbHelper.COLUMN_USER_NAME));

            Log.i(_id, name);



        }

        return c;
    }

}
