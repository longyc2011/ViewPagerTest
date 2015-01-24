
package com.stevens.viewpagertest.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FragmentViewPagerAdapter extends PagerAdapter implements
        ViewPager.OnPageChangeListener {
    private List<Fragment> fragments; // ÿ��Fragment��Ӧһ��Page
    private FragmentManager fragmentManager;
    private ViewPager viewPager; // viewPager����
    private int currentPageIndex = 0; // ��ǰpage�������л�֮ǰ��

    public FragmentViewPagerAdapter(FragmentManager fragmentManager, ViewPager viewPager,
            List<Fragment> fragments) {
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
        this.viewPager = viewPager;
        this.viewPager.setAdapter(this);
        this.viewPager.setOnPageChangeListener(this);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(fragments.get(position).getView()); // �Ƴ�viewpager����֮���page����
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = fragments.get(position);
        if (!fragment.isAdded()) { // ���fragment��û��added
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commit();
            /**
             * ����FragmentTransaction.commit()�����ύFragmentTransaction�����
             * ���ڽ��̵����߳��У����첽�ķ�ʽ��ִ�С� �����Ҫ����ִ������ȴ��еĲ�������Ҫ�������������ֻ�������߳��е��ã���
             * Ҫע����ǣ����еĻص�����ص���Ϊ��������������б�ִ����ɣ����Ҫ��ϸȷ����������ĵ���λ�á�
             */
            fragmentManager.executePendingTransactions();
        }

        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView()); // Ϊviewpager���Ӳ���
        }

        return fragment.getView();
    }

    /**
     * ��ǰpage�������л�֮ǰ��
     * 
     * @return
     */
    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        fragments.get(currentPageIndex).onPause(); // �����л�ǰFargment��onPause()
        // fragments.get(currentPageIndex).onStop(); // �����л�ǰFargment��onStop()
        if (fragments.get(i).isAdded()) {
            // fragments.get(i).onStart(); // �����л���Fargment��onStart()
            fragments.get(i).onResume(); // �����л���Fargment��onResume()
        }
        currentPageIndex = i;

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
