package com.example.seele.bottomnavdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements MainSettingsFragment.OnItemSelectedListener{

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_placeholder, new MainSettingsFragment())
                .commit();
        setTitle("Settings Main");
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fm.getBackStackEntryCount() == 0){
                    setTitle("Settings Main");
                }
            }
        });
    }

    @Override
    public void onSettingsItemClick(String settings) {
        FragmentTransaction ft = fm.beginTransaction().setCustomAnimations(R.anim.enter_from_right, 0, 0, R.anim.exit_to_right);
        ft.add(R.id.fragment_placeholder, SettingsFragment.newInstance(settings));
        ft.addToBackStack(null);
        ft.commit();
    }

}
