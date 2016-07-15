// IHandlerAidlInterface.aidl
package com.xg.androiddemo;
import com.xg.androiddemo.db.UserInfo;
import com.xg.androiddemo.IGroupInfoCallbackInterface;

// Declare any non-default types here with import statements

interface IHandlerAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    int add(int x,int y);
    String getUserInfo(in UserInfo userinfo);
    void getGroupInfo(IGroupInfoCallbackInterface callback);
}
