package com.hm.chapter_two;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.hm.chapter_two.utils.MyUtils;

/**
 * Created by Administrator on 2016/12/6.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(), Process.myPid());
        Log.e("app", "processName" + processName);
    }
}
