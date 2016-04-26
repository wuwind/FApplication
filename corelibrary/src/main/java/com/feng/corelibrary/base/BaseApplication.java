package com.feng.corelibrary.base;

import android.app.Application;
import android.content.Context;

import com.feng.corelibrary.utils.CrashHandler;
import com.feng.corelibrary.utils.NetUtil;
import com.feng.corelibrary.utils.PackageUtil;


public abstract class BaseApplication extends Application {

    public static String uid;
    public static Context context;
    public static boolean networkAvailable;
    public static CrashHandler mCrashHandler = new CrashHandler();
    public static int versionCode;
    public static String baseUrl;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        uid = PackageUtil.getMyUUID(context);
        networkAvailable = NetUtil.isConnect(context);
//        mCrashHandler.init();
        versionCode = PackageUtil.getVersionCode(this);
        init();
    }

    protected abstract void init();

}