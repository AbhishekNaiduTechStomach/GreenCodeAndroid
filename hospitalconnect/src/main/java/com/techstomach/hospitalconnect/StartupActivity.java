package com.techstomach.hospitalconnect;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

/**
 * Created by Divya on 20/9/15.
 */
public class StartupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (needStartApp())
        {
            Intent i = new Intent(StartupActivity.this, SplashScreenActivity.class);
            startActivity(i);
        }

        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // this prevents StartupActivity recreation on Configuration changes
        // (device orientation changes or hardware keyboard open/close).
        // just do nothing on these changes:
        super.onConfigurationChanged(null);
    }

    private boolean needStartApp() {
        final ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningTaskInfo> tasksInfo = am.getRunningTasks(1024);

        if (!tasksInfo.isEmpty())
        {
            final String ourAppPackageName = getPackageName();
            Log.i("MYLOG", "ourAppPackageName = " + ourAppPackageName);

            ActivityManager.RunningTaskInfo taskInfo;
            final int size = tasksInfo.size();
            for (int i = 0; i < size; i++)
            {
                taskInfo = tasksInfo.get(i);
                Log.i("MYLOG", "task info = " + taskInfo.baseActivity.getPackageName());

                if (ourAppPackageName.equals(taskInfo.baseActivity.getPackageName())) {
                    // continue application start only if there is the only Activity in the task
                    // (BTW in this case this is the StartupActivity)
                    Log.i("MYLOG", "returning = " + taskInfo.numActivities + ", " + (taskInfo.numActivities == 1));
                    return taskInfo.numActivities == 1;
                }
            }
        }

        Log.i("MYLOG", "returning = true");
        return true;
    }
}
