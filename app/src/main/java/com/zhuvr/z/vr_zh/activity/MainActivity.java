package com.zhuvr.z.vr_zh.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuvr.z.vr_zh.R;
import com.zhuvr.z.vr_zh.fragment.VrPanoFragment;
import com.zhuvr.z.vr_zh.fragment.VrVideoFragment;
import com.zhuvr.z.vr_zh.fragment.WelcomFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabs.setTabTextColors(Color.parseColor("#707070"),Color.parseColor("#FF9648"));
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#FF9648"));
        tabs.setSelectedTabIndicatorHeight(4);
        tabs.setupWithViewPager(viewpager);
    }

    private class MainFragmentAdapter extends FragmentPagerAdapter {
        private String[] mTitles = new String[]{"欢迎","VR图片", "VR电影"};
        private Fragment[] mPages = new Fragment[]{new WelcomFragment(),new VrPanoFragment(), new VrVideoFragment()};

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mPages[position];
        }


        @Override
        public int getCount() {
            return mTitles.length;
        }
    }
}
