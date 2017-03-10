package com.example.seele.bottomnavdemo;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainSettingsFragment extends Fragment {

    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onSettingsItemClick(String settings);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnItemSelectedListener");
        }
    }

    public MainSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.settings1, R.id.settings2, R.id.settings3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings1:
                handleClick("1");
                break;
            case R.id.settings2:
                handleClick("2");
                break;
            case R.id.settings3:
                handleClick("3");
                break;
        }
    }

    private void handleClick(String arg) {
        if (listener != null){
            listener.onSettingsItemClick(arg);
        }
    }

}
