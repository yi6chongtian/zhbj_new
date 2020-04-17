package com.tzb.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import com.tzb.zhbj.base.BasePager;

public class SettingPager extends BasePager {
    public SettingPager(Activity context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        mTvTitle.setText("设置中心");

        TextView textView = new TextView(this.mContext);
        textView.setText("设置中心");
        textView.setTextSize(30);
        mFlContent.addView(textView);
    }
}
