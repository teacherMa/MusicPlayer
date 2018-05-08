package com.baidu.teacherma.musicplayer;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by teacherMa on 2018/5/8
 */
public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext = null;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

}
