package com.zhl.baselibrary.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.zhl.baselibrary.fragment.BaseFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : zhuhl
 * date   : 2021/7/21
 * desc   :
 * refer  :
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> mTitleList;
    private final FragmentManager fm;
    private BaseFragment currentFragment;

    private final Map<Integer, BaseFragment> instantiateFragments = new HashMap<Integer, BaseFragment>();

    public BaseFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    public void setFragments(List<BaseFragment> fragments) {
        if (this.fragments != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.fragments) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void clean() {
        if (fm == null) {
            return;
        }
        try {
            FragmentTransaction ft = fm.beginTransaction();
            List<Fragment> frgs = fm.getFragments();
            if (frgs != null && frgs.size() > 0) {
                for (int i = 0; i < frgs.size(); i++) {
                    Fragment fr = frgs.get(i);
                    if (fr == null) {
                        continue;
                    }
                    ft.remove(fr);
                }
            }
            ft.commit();
        } catch (Exception e) {
        }
    }

    public void setPageTitle(List<String> mTitleList) {
        this.mTitleList = mTitleList;
    }

    public void setPages(List<String> mTitleList, List<BaseFragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int pos) {
        if (fragments == null) {
            return null;
        }
        return fragments.get(pos);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public int getCount() {
        if (fragments != null) {
            return fragments.size();
        } else {
            return 0;
        }
    }

    public List<String> getPageTitles() {
        return mTitleList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null) {
            if (position >= mTitleList.size()) {
                return null;
            }
            String v = mTitleList.get(position);
            if (v == null) {
                return "";
            }
            return v;
        }
        return super.getPageTitle(position);
    }

    public List<BaseFragment> getFragments() {
        return fragments;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
        //instantiateFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object instanceof BaseFragment) {
            currentFragment = (BaseFragment) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //instantiateFragments.remove(position);
        super.destroyItem(container, position, object);

    }

    public Map<Integer, BaseFragment> getInstantiateFragments() {
        return instantiateFragments;
    }

}
