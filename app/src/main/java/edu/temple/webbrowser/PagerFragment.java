package edu.temple.webbrowser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class PagerFragment extends Fragment implements PageViewerFragment.ViewerInterface{
    public PagerAdapter pagerAdapter;
    private ViewPager2 viewPager2;

    public PagerFragment() {
    }

    public static PagerFragment newInstance(ArrayList<PageViewerFragment> fragments, Object o){
        PagerFragment newFrag = new PagerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("KEY_PAGES", fragments);
        newFrag.setArguments(args);
        return newFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View l = inflater.inflate(R.layout.fragment_pager, container, false);

        viewPager2 = l.findViewById(R.id.view_pager);
        pagerAdapter = new PagerAdapter(getActivity());
        pagerAdapter.fragments.add(new PageViewerFragment());
        viewPager2.setAdapter(pagerAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
            }
        });

        return l;
    }

    public void newTabFragment() {
        pagerAdapter.fragments.add(new PageViewerFragment());
        pagerAdapter.notifyDataSetChanged();
        viewPager2.setCurrentItem(pagerAdapter.fragments.size() - 1);
    }

    public void goBack() {
        pagerAdapter.fragments.get(viewPager2.getCurrentItem()).goBack();
    }


    public void goNext() {
        pagerAdapter.fragments.get(viewPager2.getCurrentItem()).goNext();
    }

    @Override
    public String getURL(String url, String title) {
        pagerAdapter.fragments.get(viewPager2.getCurrentItem()).updateWebsite(url);
        return url;
    }

    public String getCurrentUrl() {
        return pagerAdapter.fragments.get(viewPager2.getCurrentItem()).getUrl();
    }

    public void updateWebsite(String url) {
        pagerAdapter.fragments.get(viewPager2.getCurrentItem()).updateWebsite(url);
    }


    public void goTab(int position) {
        viewPager2.setCurrentItem(position);
    }

}
