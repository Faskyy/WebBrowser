package edu.temple.webbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;


public class BrowserActivity extends AppCompatActivity implements PageControlFragment.controlInterface, PageViewerFragment.ViewerInterface,
        BrowserControlFragment.BrowserCtrlInterface, PageListFragment.PageListInterface{

    PageControlFragment controlFrag = new PageControlFragment();
    BrowserControlFragment browserFrag = new BrowserControlFragment();
    PagerFragment pagerFrag = new PagerFragment();
    PageListFragment listFrag = new PageListFragment();

    private ArrayList<PageViewerFragment> pageViewers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        setTitle("Browser Activity");

        if (savedInstanceState == null) {
            pageViewers = new ArrayList<>();
            pageViewers.add(new PageViewerFragment());
        }

        if(getSupportFragmentManager().findFragmentById(R.id.page_viewer) == null && getSupportFragmentManager().findFragmentById(R.id.page_control) == null
                && getSupportFragmentManager().findFragmentById(R.id.view_pager) == null){

            FragmentManager FM = getSupportFragmentManager();

            FragmentTransaction FT = FM.beginTransaction();

            FT.add(R.id.page_viewer, pagerFrag);
            FT.add(R.id.page_control, controlFrag);
            FT.add(R.id.browser_control, browserFrag);

            FT.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public void getURL(String url) {
        pagerFrag.updateWebsite(url);
    }

    @Override
    public void goBack() {
        pagerFrag.goBack();
    }

    @Override
    public void goNext() {
        pagerFrag.goNext();
    }

    @Override
    public void newTab() {
        pagerFrag.newTabFragment();
    }

    @Override
    public void getURL(String url, String title) {
        controlFrag.getURL(url);
        this.setTitle(title);
    }

    @Override
    public void goTab(int position) {
        pagerFrag.goTab(position);
    }

}