package com.tzb.zhbj.activity;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tzb.zhbj.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private ImageView mIvRedPoint;
    private LinearLayout mLlContainer;

    private List<ImageView> mImageViewList = new ArrayList<ImageView>();
    private int[] mImgIds = new int[]{
            R.mipmap.guide_1,
            R.mipmap.guide_2,
            R.mipmap.guide_3

    };;
    private int mPointDis = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initUI();
        initData();
    }

    private void initUI() {
        mIvRedPoint = findViewById(R.id.iv_red_point);
        mLlContainer = findViewById(R.id.ll_indicator);
    }

    private void initData() {
        for (int i = 0; i < mImgIds.length; i++) {
            //添加圆点
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i != 0) {
                params.leftMargin = 10;
            }
            imageView.setLayoutParams(params);
            mLlContainer.addView(imageView);
            //背景图片
            ImageView guidImg = new ImageView(this);
            guidImg.setImageResource(mImgIds[i]);
            mImageViewList.add(guidImg);
        }

        ViewPager viewPager = findViewById(R.id.vp_image);
        viewPager.setAdapter(new MyPageAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("tag","position:" + position + "positionOffset:" + positionOffset + "positionOffsetPixels:" + positionOffsetPixels);
                int left = (int) (mPointDis * positionOffset + position * mPointDis);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mIvRedPoint.getLayoutParams();
                params.leftMargin = left;
                mIvRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mIvRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {


            @Override
            public void onGlobalLayout() {
                mIvRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mPointDis = mLlContainer.getChildAt(1).getLeft() - mLlContainer.getChildAt(0).getLeft();
            }
        });
    }

    private class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = mImageViewList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView)object);
        }
    }
}
