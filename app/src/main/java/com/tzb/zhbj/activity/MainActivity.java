package com.tzb.zhbj.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tzb.zhbj.R;
import com.tzb.zhbj.fragment.ContentFragment;
import com.tzb.zhbj.fragment.LeftFragment;

public class MainActivity extends SlidingFragmentActivity {

    private static final String LeftFragmentTag  = "LeftFragmentTag";
    private static final String MainFragmentTag  = "MainFragmentTag";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.main_left_menu);
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindWidth(400);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_left, new LeftFragment(), LeftFragmentTag);
        transaction.replace(R.id.rl_content, new ContentFragment(), MainFragmentTag);
        transaction.commit();

    }

    public LeftFragment getLeftFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return (LeftFragment) fragmentManager.findFragmentByTag(LeftFragmentTag);
    }

    public ContentFragment getMainFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return (ContentFragment) fragmentManager.findFragmentByTag(MainFragmentTag);
    }
}
