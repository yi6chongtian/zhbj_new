package com.tzb.zhbj.base.impl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tzb.zhbj.R;
import com.tzb.zhbj.base.BasePager;

import static android.R.color.holo_green_dark;

public class HomePager extends BasePager {
    public HomePager(Activity context) {
        super(context);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initData() {
        super.initData();

        mTvTitle.setText("智慧北京");

        TextView textView = new TextView(this.mContext);
        textView.setText("我是主页");
        textView.setTextSize(30);
        mFlContent.addView(textView);
    }
}
