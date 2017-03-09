package com.example.seele.bottomnavdemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String BUSINESS_FRAG = "BUSINESS_FRAG";
    public static final String BOOK_FRAG = "BOOK_FRAG";
    public static final String MUSIC_FRAG = "MUSIC_FRAG";
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    @BindView(R.id.content_container)
    FrameLayout mContentContainer;

    private BusinessFragment businessFragment;
    private BookFragment bookFragment;
    private MusicFragment musicFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null){
            Log.e("onCreate", "creating fragments");
            businessFragment = new BusinessFragment();
            bookFragment = new BookFragment();
            musicFragment = new MusicFragment();

            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.content_container, businessFragment, BUSINESS_FRAG);
            ft.add(R.id.content_container, bookFragment, BOOK_FRAG);
            ft.add(R.id.content_container, musicFragment, MUSIC_FRAG);
            ft.commitNow();
        }else {
            Log.e("onCreate", "find fragments from savedInstanceState");
            businessFragment = (BusinessFragment) fm.findFragmentByTag(BUSINESS_FRAG);
            bookFragment = (BookFragment) fm.findFragmentByTag(BOOK_FRAG);
            musicFragment = (MusicFragment) fm.findFragmentByTag(MUSIC_FRAG);
        }



        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_business:
                        displayFragment(fm, businessFragment, BUSINESS_FRAG, bookFragment, musicFragment);
                        break;
                    case R.id.tab_book:
                        setTitle(R.string.hello_book_fragment);
                        displayFragment(fm, bookFragment, BOOK_FRAG, businessFragment, musicFragment);
                        break;
                    case R.id.tab_music:
                        setTitle(R.string.hello_music_fragment);
                        displayFragment(fm, musicFragment, MUSIC_FRAG, businessFragment, bookFragment);
                        break;
                    default:
                        break;
                }
            }
        }, true);
    }

    private void displayFragment(FragmentManager fm, Fragment fragToShow, String fragTag, Fragment fragToHide1, Fragment fragToHide2) {
        FragmentTransaction ft = fm.beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        if (fragToShow.isAdded()){
            ft.show(fragToShow);
        }else {
            ft.add(R.id.content_container, fragToShow, fragTag);
        }
        if (fragToHide1.isAdded()){ ft.hide(fragToHide1); }
        if (fragToHide2.isAdded()){ ft.hide(fragToHide2); }
        ft.commit();
    }
}
