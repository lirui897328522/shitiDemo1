package com.example.shitidemo1.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shitidemo1.R;
import com.example.shitidemo1.adapter.MyTwoViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomFragment extends Fragment {


    private ViewPager viewPager;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private TabLayout tablayout;

    public CustomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom, container, false);
        initView(view);
        initData();
        initAdapter();
        return view;
    }

    private void initAdapter() {
        MyTwoViewPagerAdapter myTwoViewPagerAdapter = new MyTwoViewPagerAdapter(getChildFragmentManager(), titleList, fragmentList);
        viewPager.setAdapter(myTwoViewPagerAdapter);
    }

    private void initData() {
        titleList = new ArrayList<>();
        titleList.add("欧美");
        titleList.add("东南亚");
        titleList.add("海岛");
        titleList.add("日韩");
        titleList.add("澳新其他");
        fragmentList = new ArrayList<>();
        fragmentList.add(new OneFragment());
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new FourFragment());
        fragmentList.add(new FiveFragment());
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(viewPager);
    }
}
