package edu.temple.webbrowser;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class BookmarksActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        //generate list
        ArrayList<String> list = new ArrayList<String>();
        list.add("Reddit");
        list.add("YouTube");
        list.add("Facebook");

        //instantiate custom adapter
        BookmarkAdapter adapter = new BookmarkAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.list_view);
        lView.setAdapter(adapter);


    }
}