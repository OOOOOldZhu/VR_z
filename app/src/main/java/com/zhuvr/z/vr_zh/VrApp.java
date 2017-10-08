package com.zhuvr.z.vr_zh;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;

public class VrApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }
}
