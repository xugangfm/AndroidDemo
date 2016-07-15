package com.xg.androiddemo.db;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by xugang on 16/7/15.
 */
public class GroupInfo  implements Parcelable {

    String TAG = this.getClass().getSimpleName();


    public  String  user_id ;
    public  String  user_name ;
    public  String  extra_info ;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getExtra_info() {
        return extra_info;
    }

    public void setExtra_info(String extra_info) {
        this.extra_info = extra_info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_id);
        dest.writeString(user_name);
        dest.writeString(extra_info);
        Log.i(TAG, "writeToParcel: 序列化");
    }

    public GroupInfo(){

    }
    public GroupInfo(Parcel source){
        user_id = source.readString();
        user_name = source.readString();
        extra_info =source.readString();
        Log.i(TAG, "UserInfo: 反序列化");
    }

    public static final Parcelable.Creator<GroupInfo> CREATOR = new Creator<GroupInfo>() {
        @Override
        public GroupInfo createFromParcel(Parcel source) {
            return new GroupInfo(source);
        }

        @Override
        public GroupInfo[] newArray(int size) {
            return new GroupInfo[size];
        }
    };
}