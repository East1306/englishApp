package com.nhi.english.Revise_PhuongDong.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nhi.english.Revise_PhuongDong.Fragment.FileViewerFragment;
//import com.nhi.english.Revise_PhuongDong.Fragment.RecordFragment;

public class MyTabAdapter extends FragmentPagerAdapter {

    String[] titles ={"Record", "Saved Recording"};
    public MyTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override


    public Fragment getItem(int position) {
        switch (position){
            case 1:
//                return new RecordFragment();
            case 2:
                return new FileViewerFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
