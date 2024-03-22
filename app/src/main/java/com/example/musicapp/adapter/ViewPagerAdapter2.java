package com.example.musicapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicapp.fragment.AlbumFragment;
import com.example.musicapp.fragment.LibraryFragment;
import com.example.musicapp.fragment.SongFragment;

public class ViewPagerAdapter2 extends FragmentStateAdapter {
    public ViewPagerAdapter2(@NonNull LibraryFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SongFragment();
            case 1:
                return new AlbumFragment();
            default:
                return new SongFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
