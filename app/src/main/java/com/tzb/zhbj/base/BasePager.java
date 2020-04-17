package com.tzb.zhbj.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tzb.zhbj.R;
import com.tzb.zhbj.activity.MainActivity;

public class BasePager {

    protected final Activity mContext;
    protected TextView mTvTitle;
    protected ImageButton mIbMenu;
    protected FrameLayout mFlContent;
    public View mRootView;

    public BasePager(Activity context) {
        this.mContext = context;
        mRootView = initView();
    }

    /*
    * 初始化布局
    * */
    public View initView() {
        View view = View.inflate(this.mContext, R.layout.base_pager, null);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIbMenu = view.findViewById(R.id.ib_left);
        mFlContent = view.findViewById(R.id.fl_content);
        return view;
    }

    /*
    * 初始化数据
    * */
    public void initData() {

    }
    
}
