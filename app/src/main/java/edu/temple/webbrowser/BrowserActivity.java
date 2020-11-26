package edu.temple.webbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class BrowserActivity extends AppCompatActivity implements PageControlFragment.controlInterface, PageViewerFragment.ViewerInterface,
        BrowserControlFragment.BrowserCtrlInterface, PageListFragment.PageListInterface{

    PageControlFragment controlFrag = new PageControlFragment();
    BrowserControlFragment browserFrag = new BrowserControlFragment();
    PagerFragment pagerFrag = new PagerFragment();
    PageListFragment listFrag = new PageListFragment();
    private final String FILENAME_KEY = "bookmarks";
    String bookmarkString;

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
    public void saveBookmark() {
        boolean fileExists = false;
        String contents;
        String saveThis;
        FileInputStream fis = null;

        try {
            fis = this.openFileInput(FILENAME_KEY);
            fileExists = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(fileExists) {
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            contents = stringBuilder.toString();
        } else {
            contents = "";
        }

        if(contents.length() == 0)
            saveThis = pagerFrag.getCurrentUrl() + "," + pagerFrag.getCurrentUrl();
        else
            saveThis = contents + "," + pagerFrag.getCurrentUrl() + "," + pagerFrag.getCurrentUrl();


        try (FileOutputStream fos = this.openFileOutput(FILENAME_KEY, this.MODE_PRIVATE)){
            fos.write(saveThis.getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "Bookmark was saved successfully", Toast.LENGTH_SHORT).show();
        bookmarkString = contents;
    }

    @Override
    public void openBookmarks() {
        Intent intent = new Intent(this, BookmarksActivity.class);
        startActivityForResult(intent, 1);
    }


    @Override
    public String getURL(String url, String title) {
        controlFrag.getURL(url);
        this.setTitle(title);
        return url;
    }

    @Override
    public void goTab(int position) {
        pagerFrag.goTab(position);
    }

}
