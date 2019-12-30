package com.yunsai.ops.store_management.adapter;


import com.yunsai.ops.store_management.home.HomeFragment;
import com.yunsai.ops.store_management.home1.Home1Fragment;
import com.yunsai.ops.store_management.home2.Home2Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewpagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = new Fragment[]{new HomeFragment(),new Home2Fragment()};

    public ViewpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
	
}
