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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PageListFragment extends Fragment {

    ListView pageList;
    PageListInterface listInterface;
    ArrayList<String> titles;
    ArrayList<String> urls;
    ArrayList<TextView> textViews = new ArrayList<>();
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

//       titles = browserActivity.getTitles();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_list, container, false);
        pageList = l.findViewById(R.id.page_list);

        pageList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return pageViewerFragments.size();
            }

            @Override
            public Object getItem(int position) {
                return (String) pageViewerFragments.get(position).getPageTitle();
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView;

                if(convertView == null) {
                    textView = new TextView((Context) listInterface);
                    textView.setPadding(3,3,3,3);
                    textView.setTextSize(20);
                }
                else
                    textView = (TextView) convertView;

                if (pageViewerFragments.isEmpty()) textView.setText("New Page");
                else
                    textView.setText(pageViewerFragments.get(position).getPageTitle());

                return textView;
            }
        });

        pageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listInterface.goTab(position);
            }
        });

        return l;
    }

    public void update(ArrayList<PageViewerFragment> pageViewerFragments){
        this.pageViewerFragments = pageViewerFragments;

    }
    interface PageListInterface {
        void goTab(int position);
    }
}