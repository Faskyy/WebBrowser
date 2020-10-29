package edu.temple.webbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.control, PageViewerFragment.viewer{

    PageControlFragment controlFrag = new PageControlFragment();
    PageViewerFragment viewFrag = new PageViewerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        setTitle("Browser Activity");

        if(getSupportFragmentManager().findFragmentById(R.id.page_viewer) == null && getSupportFragmentManager().findFragmentById(R.id.page_control) == null){

            viewFrag = new PageViewerFragment();
            FragmentManager FM = getSupportFragmentManager();

            FragmentTransaction FT = FM.beginTransaction();

            FT.add(R.id.page_viewer, viewFrag);
            FT.add(R.id.page_control, controlFrag);
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
    public void SetURL(String url) {
        controlFrag.updateUrlEditText(url);
    }

    @Override
    public void getURL(String url) {
        viewFrag.updateWebsite(url);
    }

    @Override
    public void goBack() {
        viewFrag.goBack();
    }

    @Override
    public void goNext() {
        viewFrag.goNext();
    }
}