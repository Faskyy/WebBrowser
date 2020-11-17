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
    public void onPause() {
        super.onPause();
        //  getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
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

    interface PageListInterface {
        void goTab(int position);
    }
}