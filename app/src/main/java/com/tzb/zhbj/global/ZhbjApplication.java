package com.tzb.zhbj.global;

import android.app.Application;

import org.xutils.x;

public class ZhbjApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
