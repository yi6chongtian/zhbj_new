package com.tzb.zhbj.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tzb.zhbj.R;
import com.tzb.zhbj.activity.MainActivity;
import com.tzb.zhbj.base.BasePager;
import com.tzb.zhbj.base.impl.GovAffairsPager;
import com.tzb.zhbj.base.impl.HomePager;
import com.tzb.zhbj.base.impl.NewsCenterPager;
import com.tzb.zhbj.base.impl.SettingPager;
import com.tzb.zhbj.base.impl.SmartServicePager;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends BaseFragment {
    private ArrayList<BasePager> mPagerList = new ArrayList<>();
    private ViewPager mViewPager;

    @Override
    View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mViewPager = view.findViewById(R.id.view_pager);
        RadioGroup radioGroup = view.findViewById(R.id.rg_tabs);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_news:
                            mViewPager.setCurrentItem(1, false);

                            break;
                    case R.id.rb_service:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(4, false);
                        break;
                        default:
                            break;
                }
            }
        });
        return view;
    }

    @Override
    void initData() {

        HomePager homePager = new HomePager(mActivity);
        NewsCenterPager newsCenterPager = new NewsCenterPager(mActivity);
        SmartServicePager smartServicePager = new SmartServicePager(mActivity);
        GovAffairsPager govAffairsPager = new GovAffairsPager(mActivity);
        SettingPager settingPager = new SettingPager(mActivity);
        mPagerList.add(homePager);
        mPagerList.add(newsCenterPager);
        mPagerList.add(smartServicePager);
        mPagerList.add(govAffairsPager);
        mPagerList.add(settingPager);

        mViewPager.setAdapter(new ContentAdapter());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BasePager pager = mPagerList.get(position);
                pager.initData();
                if (position == 0 || position == mPagerList.size() - 1) {
                    // 首页和设置页要禁用侧边栏
                    setSlidingMenuEnable(false);
                } else {
                    // 其他页面开启侧边栏
                    setSlidingMenuEnable(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homePager.initData();
    }

    private void setSlidingMenuEnable(boolean enable) {
        MainActivity mainActivity = (MainActivity) mActivity;
        SlidingMenu menu = mainActivity.getSlidingMenu();
        if (enable) {
            menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        } else {
            menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

    }

    private class ContentAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager pager = mPagerList.get(position);
            container.addView(pager.mRootView);
            return  pager.mRootView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = mPagerList.get(position).mRootView;
            container.removeView(view);
        }
    }
}
