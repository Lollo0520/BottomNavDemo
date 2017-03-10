package com.example.seele.bottomnavdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingsFragment extends Fragment {

    @BindView(R.id.setting_tv)
    TextView mSettingTv;

    private static final String ARGS = "args";
    private String mPage;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String page) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARGS, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getString(ARGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        mSettingTv.setText("#Settings: "+mPage);
        getActivity().setTitle("#Settings: "+mPage);
        return view;
    }
}
