package com.example.seele.bottomnavdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BusinessFragment extends Fragment {


    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    public BusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        ButterKnife.bind(this, view);
        mViewpager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()));
        mTablayout.setupWithViewPager(mViewpager);
        final TabLayout.ViewPagerOnTabSelectedListener onTabSelectedListener = new TabLayout.ViewPagerOnTabSelectedListener(mViewpager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getActivity().setTitle(tab.getText());
            }
        };
        mTablayout.addOnTabSelectedListener(onTabSelectedListener);

        mTablayout.post(new Runnable() {
            @Override
            public void run() {
                onTabSelectedListener.onTabSelected(mTablayout.getTabAt(mTablayout.getSelectedTabPosition()));
            }
        });

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            getActivity().setTitle(mTablayout.getTabAt(mTablayout.getSelectedTabPosition()).getText());
        }
    }

    private static class SampleFragmentPagerAdapter extends FragmentPagerAdapter{

        private String[] tabTitles = new String[]{"Meetings", "Files"};

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(String.valueOf(position+1));
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}
