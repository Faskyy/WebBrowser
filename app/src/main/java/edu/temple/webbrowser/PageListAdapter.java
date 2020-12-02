package edu.temple.webbrowser;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PageListAdapter extends BaseAdapter {

    ArrayList<String> titles;
    Context context;

    public PageListAdapter(Context context, ArrayList<String> titles) {
        this.titles = titles;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null){
            textView = (TextView) convertView;
        } else {
            textView = new TextView(context);
            textView.setPadding(5, 8, 8, 5);
            textView.setTextSize(15);
        }
        textView.setText(getItem(position).toString());
        textView.setBackgroundColor(Color.WHITE);
        return textView;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }


}
