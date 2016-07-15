// IGroupInfoCallbackInterface.aidl
package com.xg.androiddemo;
import com.xg.androiddemo.db.GroupInfo;

// Declare any non-default types here with import statements

interface IGroupInfoCallbackInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onSuccess(in GroupInfo groupinfo);
}
