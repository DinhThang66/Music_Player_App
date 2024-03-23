package com.example.musicapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.musicapp.MainActivity2;
import com.example.musicapp.R;
import com.example.musicapp.adapter.ViewPagerAdapter2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class LibraryFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    ViewPagerAdapter2 viewPagerAdapter2;
    ImageView search_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        initViews(view);

        viewPagerAdapter2 = new ViewPagerAdapter2(this);
        mViewPager.setAdapter(viewPagerAdapter2);

        new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                switch (i){
                    case 0:
                        tab.setText("Playlist");
                        break;
                    case 1:
                        tab.setText("Album");
                        break;
                }

            }
        }).attach();


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();;
                fragmentTransaction.replace(R.id.main, new SearchFragment());
                fragmentTransaction.addToBackStack(SearchFragment.TAG);
                fragmentTransaction.commit();
            }
        });


        return view;
    }

    private void initViews(View view) {
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.view_pager);
        search_btn = view.findViewById(R.id.search_btn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}