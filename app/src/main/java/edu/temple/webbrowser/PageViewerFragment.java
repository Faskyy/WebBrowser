package edu.temple.webbrowser;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PageViewerFragment extends Fragment {
    WebView browser;
    ViewerInterface viewerInterface;
    String setURL;
    String setTitle;


    public PageViewerFragment() {
        // Required empty public constructor
    }


    protected PageViewerFragment(Parcel in) {
    }

    public static final Parcelable.Creator<PageViewerFragment> CREATOR = new Parcelable.Creator<PageViewerFragment>() {
        @Override
        public PageViewerFragment createFromParcel(Parcel in) {
            return new PageViewerFragment(in);
        }

        @Override
        public PageViewerFragment[] newArray(int size) {
            return new PageViewerFragment[size];
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        browser.saveState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            browser.loadUrl("https://www.google.com");
        } else {
            browser.restoreState(savedInstanceState.getBundle("web"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page_viewer, container, false);
        browser = v.findViewById(R.id.webView);
        browser.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                if (viewerInterface != null) {
                    viewerInterface.getURL(url, browser.getTitle());
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (viewerInterface != null) {
                    viewerInterface.getURL(url, browser.getTitle());
                }
            }
        });

        return v;
    }

    public void goBack() {
        if (browser.canGoBack()) {
            browser.goBack();
        }
    }

    public void goNext() {
        if (browser.canGoForward()) {
            browser.goForward();
        }
    }

    public String getPageTitle() {
        return browser.getTitle();
    }

    /*public void updateText(){
        browser.getTitle();
    }*/

    public void updateWebsite(String url) {
        if (url.indexOf("https://", 0) == -1) {
            url = "https://" + url;
        }
        browser.loadUrl(url);
    }


    interface ViewerInterface {
        void getURL(String url, String title);
    }

}