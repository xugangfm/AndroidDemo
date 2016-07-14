package com.xg.androiddemo.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.xg.androiddemo.receiver.HeartbeatReceiver;

/**
 * Created by DragonJ on 14-6-23.
 */
public class HeartbeatUtils {

    private static final int HEARTBEAT_SPAN = 1000 * 1; // 10 seconds

    public static void startNextHeartbeat(Context context) {

        RLog.i(context,  "startNextHeartbeat" , context.getPackageName());

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

    public static void cancelHeartbeat(Context context) {
        RLog.i(context,  "cancelHeartbeat" , context.getPackageName());

        Intent heartbeatIntent = new Intent(context, HeartbeatReceiver.class);
        heartbeatIntent.setPackage(context.getPackageName());
        PendingIntent intent = PendingIntent.getBroadcast(context, 0, heartbeatIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.cancel(intent);
    }
}
