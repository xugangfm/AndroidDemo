package com.xg.androiddemo.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import io.rong.common.RLog;

/**
 * Created by DragonJ on 14-6-23.
 */
class WakeLockUtils {

    private static final int HEARTBEAT_SPAN = 1000 * 60 * 3; // 10 seconds

    static void startNextHeartbeat(Context context) {

        RLog.d(context,  "startNextHeartbeat" , context.getPackageName());

        Intent heartbeatIntent = new Intent(context, HeartbeatReceiver.class);
        heartbeatIntent.setPackage(context.getPackageName());
        PendingIntent intent = PendingIntent.getBroadcast(context, 0, heartbeatIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        long time = SystemClock.elapsedRealtime() + HEARTBEAT_SPAN;
        //Cancel old
        alarmManager.cancel(intent);
        //Obtain new
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, intent);
    }

    static void cancelHeartbeat(Context context) {
        RLog.d(context,  "cancelHeartbeat" , context.getPackageName());

        Intent heartbeatIntent = new Intent(context, HeartbeatReceiver.class);
        heartbeatIntent.setPackage(context.getPackageName());
        PendingIntent intent = PendingIntent.getBroadcast(context, 0, heartbeatIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.cancel(intent);
    }
}
