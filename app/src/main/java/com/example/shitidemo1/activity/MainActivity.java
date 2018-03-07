package com.example.shitidemo1.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.shitidemo1.R;
import com.example.shitidemo1.adapter.MyViewPagerAdapter;
import com.example.shitidemo1.fragment.CustomFragment;
import com.example.shitidemo1.fragment.HeadFragment;
import com.example.shitidemo1.fragment.LocationFragment;
import com.example.shitidemo1.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {
    private ViewPager vp;
    private FrameLayout tabcontent;
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private String[] tabText = { "首页", "定制", "当地玩乐", "我的" };
    private int[] imageRes = new int[] { R.drawable.head, R.drawable.custom, R.drawable.location, R.drawable.my };
    private List<Fragment> list = new ArrayList<Fragment>();
    MyViewPagerAdapter myViewPagerAdapter;
    private Class[] fragments = new Class[] { HeadFragment.class, CustomFragment.class, LocationFragment.class, MyFragment.class };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPager();
    }

    private void initPager() {
        list.add(new HeadFragment());
        list.add(new CustomFragment());
        list.add(new LocationFragment());
        list.add(new MyFragment());
         myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), list);
         vp.setAdapter(myViewPagerAdapter);
        mTabHost.getTabWidget().setDividerDrawable(null);
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        vp.addOnPageChangeListener(this);//设置页面切换时的监听器
        mTabHost.setup(this, getSupportFragmentManager(), R.id.vp);//绑定viewpager
        mTabHost.setOnTabChangedListener(this);

        /*新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment*/
        for (int i = 0; i < tabText.length; i++) {

            View view = LayoutInflater.from(this).inflate(R.layout.tab_content, null);
            ((TextView) view.findViewById(R.id.tv)).setText(tabText[i]);
            ((ImageView) view.findViewById(R.id.iv)).setImageResource(imageRes[i]);
            // 给每个Tab按钮设置标签、图标和文字
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabText[i])
                    .setIndicator(view);
            // 将Tab按钮添加进Tab选项卡中，并绑定Fragment
            mTabHost.addTab(tabSpec, fragments[i], null);
            mTabHost.setTag(i);
          /*  mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.textcolor_selector);//设置Tab被选中的时候颜色改变*/
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);//设置View覆盖子类控件而直接获得焦点
        mTabHost.setCurrentTab(position);//根据位置Postion设置当前的Tab
        widget.setDescendantFocusability(oldFocusability);//设置取消分割线
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int position = mTabHost.getCurrentTab();
        vp.setCurrentItem(position);//把选中的Tab的位置赋给适配器，让它控制页面切换
    }
}
