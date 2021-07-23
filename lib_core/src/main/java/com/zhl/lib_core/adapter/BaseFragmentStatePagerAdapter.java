package com.zhl.lib_core.adapter;

import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.zhl.lib_core.fragment.BaseFragment;

import java.util.List;

/**
 * author : zhuhl
 * date   : 2021/7/21
 * desc   :
 * refer  :
 */
public class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private final FragmentManager fm;
    private List<BaseFragment> fragments;
    private List<String> mTitleList;

    private BaseFragment currentFragment;

    public BaseFragmentStatePagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public List<String> getPageTitles() {
        return mTitleList;
    }

    public void setFragments(List<BaseFragment> fragments) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (this.fragments != null) {
            for (BaseFragment fragment : this.fragments) {
                fragmentTransaction.remove(fragment);
            }
            try{
                fragmentTransaction.commit();
            }catch (IllegalStateException e){
                //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
            }
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }
    public List<BaseFragment> getFragments(){
        return fragments;
    }

    public void setPageTitle(List<String> mTitleList) {
        this.mTitleList = mTitleList;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object instanceof BaseFragment) {
            currentFragment = (BaseFragment) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList == null || position < 0 || position > mTitleList.size() - 1) {
            return super.getPageTitle(position);

        }
        return mTitleList.get(position);
    }


    @Override
    public Fragment getItem(int position) {
        if (fragments == null) {
            return null;
        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if (fragments != null) {
            return fragments.size();
        }
        return 0;
    }
}
