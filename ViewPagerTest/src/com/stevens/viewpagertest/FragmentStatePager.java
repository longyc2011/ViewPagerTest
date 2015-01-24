/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stevens.viewpagertest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.stevens.viewpagertest.fragment.ArrayListFragment;
import com.stevens.viewpagertest.fragment.BandwidthFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentStatePager extends FragmentActivity implements FragmentController {
    static final int NUM_ITEMS = 7;
    private static final String TAG = "FragmentStatePager";
    MyAdapter mAdapter;

    ViewPager mPager;

    List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pager);

        fragmentList.add(ArrayListFragment.newInstance(0));

        fragmentList.add(ArrayListFragment.newInstance(1));
        fragmentList.add(ArrayListFragment.newInstance(2));
        fragmentList.add(ArrayListFragment.newInstance(3));
        fragmentList.add(new BandwidthFragment());
        // fragmentList.add(ArrayListFragment.newInstance(4));
        fragmentList.add(ArrayListFragment.newInstance(5));
        fragmentList.add(ArrayListFragment.newInstance(6));
        mAdapter = new MyAdapter(getSupportFragmentManager(), fragmentList);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOnPageChangeListener(mAdapter);
        mPager.setAdapter(mAdapter);

        // Watch for button clicks.
        Button button = (Button) findViewById(R.id.goto_first);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(0);
            }
        });
        button = (Button) findViewById(R.id.goto_last);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(NUM_ITEMS - 1);
            }
        });
    }

    public static class MyAdapter extends FragmentStatePagerAdapter implements OnPageChangeListener {
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public MyAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {

            return fragmentList.get(position);
        }

        public void changeFragment() {
            fragmentList.remove(4);
            fragmentList.add(4, new BandwidthFragment());
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

            Log.d(TAG, "onPageScrolled arg0 == " + arg0);
            Log.d(TAG, "onPageScrolled arg1 == " + arg1);
            Log.d(TAG, "onPageScrolled arg2 == " + arg2);
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            Log.d(TAG, "onPageScrollStateChanged arg0 == " + arg0);
        }

        @Override
        public void onPageSelected(int arg0) {
            Log.d(TAG, "onPageSelected arg0 == " + arg0);
            if (arg0 == 5 || arg0 == 3) {
                changeFragment();
            }
        }
    }

    @Override
    public void changeFragment() {
        fragmentList.remove(4);
        fragmentList.add(4, ArrayListFragment.newInstance(4));
        mAdapter = new MyAdapter(getSupportFragmentManager(), fragmentList);
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(mAdapter);
        // ((MyAdapter) mPager.getAdapter()).changeFragment();
        mPager.setCurrentItem(4);

    }

}
