package edu.temple.webbrowser;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
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


    private ArrayList<String> list;
    ArrayList<String> urls;
    Context context;
    public final String FILENAME_KEY = "bookmarks";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        getSupportActionBar().setTitle("Bookmarks");
        context = this;

        boolean fileExists = false;
        String contents;
        FileInputStream fis = null;

        try {
            fis = this.openFileInput(FILENAME_KEY);
            fileExists = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fileExists) {
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

        //generate my lists and urls,, created a temporary array to split content
        list = new ArrayList<>();
        urls = new ArrayList<>();
        String[] temporary = contents.split("-");

        //displaying number of bookmarks without having to scroll to see them
        for (int i = 0; i < temporary.length; i++) {
            Log.println(Log.ASSERT, "temp[" + i + "]", temporary[i]);
            String[] temp2 = temporary[i].split(",");
            list.add(temp2[0]);
            list.add(temp2[1]);
            list.add(temp2[2]);
            list.add(temp2[3]);
            list.add(temp2[4]);
            list.add(temp2[5]);
            list.add(temp2[6]);
            list.add(temp2[7]);
            list.add(temp2[8]);
            urls.add(temp2[i]);
        }

        //instantiated the custom adapter
        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(list, urls, this);

        //handling my listview and assigned the adapter
        ListView lView = (ListView)findViewById(R.id.list_view);
        lView.setAdapter(bookmarkAdapter);

        // clears listView content
        // lView.setAdapter(null);
        lView.setLongClickable(true);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, BrowserActivity.class);
                intent.putExtra("url", urls.get(i));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
