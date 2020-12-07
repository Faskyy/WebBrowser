package edu.temple.webbrowser;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;


public class PagerAdapter extends FragmentStateAdapter {
    public ArrayList<PageViewerFragment> fragments = new ArrayList<>();

    public PagerAdapter(@NonNull FragmentActivity fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }


}

