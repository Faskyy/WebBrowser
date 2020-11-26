package edu.temple.webbrowser;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class BrowserControlFragment extends Fragment {

    private ArrayList<String> list = new ArrayList<String>();
    ImageButton btnTab;
    ImageButton btnBookmark;
    ImageButton btnDisplay;
    BrowserCtrlInterface browserInterface;
    ArrayList<String> itemList;

    public BrowserControlFragment() {
        // Required empty public constructor
    }
    String[] items = {"Test"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_browser_control, container, false);

        btnTab = v.findViewById(R.id.btnTab);
        btnBookmark = v.findViewById(R.id.btnBookmark);
        btnDisplay = v.findViewById(R.id.btnDisplay);


        btnTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserInterface.newTab();
            }
        });


        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                browserInterface.saveBookmark();
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserInterface.openBookmarks();
            }
        });

        return v;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BrowserCtrlInterface) {
            browserInterface = (BrowserControlFragment.BrowserCtrlInterface) context;
        } else {
            throw new RuntimeException("You must implement the control interface to use BrowserControlFragment");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        browserInterface = null;
    }


    public interface BrowserCtrlInterface {
        void newTab();
        void saveBookmark();
        void openBookmarks();
    }

}