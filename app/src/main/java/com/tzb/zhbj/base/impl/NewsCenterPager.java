package com.tzb.zhbj.base.impl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tzb.zhbj.activity.MainActivity;
import com.tzb.zhbj.base.BaseMenuDetailPager;
import com.tzb.zhbj.base.BasePager;
import com.tzb.zhbj.base.impl.menu.InteractMenuDetailPager;
import com.tzb.zhbj.base.impl.menu.NewsMenuDetailPager;
import com.tzb.zhbj.base.impl.menu.PhotosMenuDetailPager;
import com.tzb.zhbj.base.impl.menu.TopicMenuDetailPager;
import com.tzb.zhbj.domain.NewsMenu;
import com.tzb.zhbj.fragment.LeftFragment;
import com.tzb.zhbj.global.GlobalConstants;
import com.tzb.zhbj.utils.CacheUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import static android.R.color.holo_green_dark;

public class NewsCenterPager extends BasePager {

    private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;// 菜单详情页集合
    private NewsMenu mNewsData;// 分类信息网络数据

    public NewsCenterPager(Activity context) {
        super(context);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initData() {
        super.initData();

        mTvTitle.setText("新闻中心");

        TextView textView = new TextView(this.mContext);
        textView.setText("新闻中心");
        textView.setTextSize(30);
        mFlContent.addView(textView);

        String json = CacheUtils.getCache(GlobalConstants.CATEGORY_URL, mContext);
        if (!TextUtils.isEmpty(json)) {
            procesData(json);
        }
        getServerData();

    }

    private void procesData(String json) {
        mNewsData = new Gson().fromJson(json, NewsMenu.class);
        MainActivity mainActivity = (MainActivity) mContext;
        LeftFragment leftFragment = mainActivity.getLeftFragment();
        leftFragment.setMenuData(mNewsData.data);

        // 初始化4个菜单详情页
        mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPagers.add(new NewsMenuDetailPager(mContext));
        mMenuDetailPagers.add(new TopicMenuDetailPager(mContext));
        mMenuDetailPagers.add(new PhotosMenuDetailPager(mContext));
        mMenuDetailPagers.add(new InteractMenuDetailPager(mContext));

        // 将新闻菜单详情页设置为默认页面
        setCurrentDetailPager(0);
    }

    // 设置菜单详情页
    public void setCurrentDetailPager(int position) {
        // 重新给frameLayout添加内容
        BaseMenuDetailPager pager = mMenuDetailPagers.get(position);// 获取当前应该显示的页面
        View view = pager.mRootView;// 当前页面的布局

        // 清除之前旧的布局
        mFlContent.removeAllViews();

        mFlContent.addView(view);// 给帧布局添加布局

        // 初始化页面数据
        pager.initData();

        // 更新标题
        mTvTitle.setText(mNewsData.data.get(position).title);
    }

    private void getServerData() {
        RequestParams params = new RequestParams(GlobalConstants.CATEGORY_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("tag", result);
                NewsMenu menu = new Gson().fromJson(result, NewsMenu.class);
                Log.i("tag", menu.toString());
                CacheUtils.setCache(GlobalConstants.CATEGORY_URL, result, mContext);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
