package edu.temple.webbrowser;


import android.content.Context;
import android.os.Bundle;;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class PageControlFragment extends Fragment {

    private static final String KEY_URL = "KEY_URL";

    control pageControl;
    private String url;
    EditText et;
    ImageButton btnGo, btnBack, btnNext;

    public static PageControlFragment newInstance(String url) {
        PageControlFragment fragment = new PageControlFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(KEY_URL);
        }
        if (savedInstanceState == null) return;
        url = savedInstanceState.getString("KEY_URL");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_control, container, false);

        et = l.findViewById(R.id.editTextURL);

        btnGo = l.findViewById(R.id.btnGo);
        btnNext = l.findViewById(R.id.btnNext);
        btnBack = l.findViewById(R.id.btnBack);

        btnGo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                url = et.getText().toString();
                pageControl.getURL(url);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pageControl.goBack();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pageControl.goNext();
            }
        });

        return l;
    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        pageControl = (control) context;
        if (context instanceof control) {
            pageControl = (control) context;
        } else {
            throw new RuntimeException("You must implement the control interface to attach this fragment");
        }
    }

    public void onDetach(){
        super.onDetach();
        pageControl = null;

    }


    public interface control{
        void getURL(String url);
        void goBack();
        void goNext();
    }

    public void updateUrlEditText(String url){
        et.setText(url);
    }

}