package com.xg.androiddemo.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by xugang on 16/7/8.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "user.db";

    public static final String DB_TABLE = "user_info";
    public static final String _ID = "_id";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_EXTRA_INFO = "extra_info";

    public DBHelper(Context context){
        super(context,DB_NAME,null,1);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{

            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            db.execSQL("CREATE TABLE IF NOT EXISTS " + DB_TABLE + "( " +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_USER_ID + " TEXT," +
//                    COLUMN_USER_SOURCE + " INTEGER DEFAULT -1," +
                    COLUMN_USER_NAME + " TEXT," +
//                    COLUMN_USER_AVATAR + " TEXT," +
//                    COLUMN_USER_TYPE + " INTEGER DEFAULT -1," +
//                    COLUMN_USER_GENDER + " INTEGER DEFAULT 0," +
//                    COLUMN_NICK_NAME + " TEXT," +
//                    COLUMN_LEVEL + " INTEGER DEFAULT 0," +
//                    COLUMN_REMARK + " TEXT," +
                    COLUMN_EXTRA_INFO + " TEXT);");


        }catch (SQLException e){
            Log.e("couldn't create table ", e.toString());
        }


    }
}
