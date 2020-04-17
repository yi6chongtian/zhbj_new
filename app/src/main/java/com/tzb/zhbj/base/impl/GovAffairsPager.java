package com.tzb.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import com.tzb.zhbj.base.BasePager;

public class GovAffairsPager extends BasePager {
    public GovAffairsPager(Activity context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();

        mTvTitle.setText("政务");

        TextView textView = new TextView(this.mContext);
        textView.setText("我是政务事务");
        textView.setTextSize(30);
        mFlContent.addView(textView);
    }
}
