package com.tzb.zhbj.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tzb.zhbj.R;
import com.tzb.zhbj.activity.MainActivity;
import com.tzb.zhbj.base.impl.NewsCenterPager;
import com.tzb.zhbj.domain.NewsMenu;

import java.util.List;

public class LeftFragment extends BaseFragment {
    private int mCurrentPos = 0;
    private BaseAdapter mAdapter;

    private ListView mLvList;
    private List<NewsMenu.NewsMenuData> mMenuList;

    @Override
    View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left, null);
        mLvList = view.findViewById(R.id.lv_menu);
        return view;
    }

    @Override
    void initData() {

    }

    public void setMenuData(List<NewsMenu.NewsMenuData> menu) {
        mMenuList = menu;
        mAdapter = new MenuAdapter();
        mLvList.setAdapter(mAdapter);
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos = position;// 更新当前被选中的位置
                mAdapter.notifyDataSetChanged();// 刷新listview

                // 收起侧边栏
                toggle();

                // 侧边栏点击之后, 要修改新闻中心的FrameLayout中的内容
                setCurrentDetailPager(position);

            }
        });
    }

    private void toggle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();
    }

    private void setCurrentDetailPager(int position) {
        // 获取新闻中心的对象
        MainActivity mainUI = (MainActivity) mActivity;
        // 获取ContentFragment
        ContentFragment fragment = mainUI.getMainFragment();
        // 获取NewsCenterPager
        NewsCenterPager newsCenterPager = fragment.getNewsCenterPager();
        // 修改新闻中心的FrameLayout的布局
        newsCenterPager.setCurrentDetailPager(position);
    }

    private class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mMenuList.size();
        }

        @Override
        public NewsMenu.NewsMenuData getItem(int position) {
            return mMenuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.list_item_left_menu, null);
            TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);

            NewsMenu.NewsMenuData item = getItem(position);
            tvMenu.setText(item.title);

            if (position == mCurrentPos) {
                // 被选中
                tvMenu.setEnabled(true);// 文字变为红色
            } else {
                // 未选中
                tvMenu.setEnabled(false);// 文字变为白色
            }

            return view;
        }
    }

    
}
