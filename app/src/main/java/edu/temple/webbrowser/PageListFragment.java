package edu.temple.webbrowser;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ListView;


import java.util.ArrayList;


public class PageListFragment extends Fragment {

    ListView pageList;
    PageListInterface listInterface;
    PageListAdapter pageListAdapter;
    ArrayList<String> titles;
    ArrayList<PageViewerFragment> pageViewerFragments;
    Context context;


    public PageListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof PageListFragment.PageListInterface) {
            listInterface = (PageListFragment.PageListInterface) context;
        } else {
            throw new RuntimeException("You must implement PageListInterface to use PageListFragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page_list, container, false);
        pageList = v.findViewById(R.id.page_list);


        pageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listInterface.goTab(position);
            }
        });
        return v;
    }

    public void addTitle(String str) {
        if (pageListAdapter == null) {
            titles.add(" ");
            pageListAdapter = new PageListAdapter(getContext(), titles);
        }
        pageListAdapter.addTitle(str);
    }

    public void updateTitle(String title, int index) {
        if (pageListAdapter == null) {
            titles.add(" ");
            pageListAdapter = new PageListAdapter(getContext(), titles);
        }
        pageListAdapter.setTitle(title, index);
    }


    public void update(ArrayList<PageViewerFragment> pageViewerFragments){
        this.pageViewerFragments = pageViewerFragments;

    }

    interface PageListInterface {
        void goTab(int position);
    }
}