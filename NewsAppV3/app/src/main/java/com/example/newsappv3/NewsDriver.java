package com.example.newsappv3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class NewsDriver extends AppCompatActivity {


    String API_KEY = "47da75493074479c95323798e6a853ea"; // secret key
    ListView listNews;
    ProgressBar loader;

    // Storing each article in a HashMap as a key,value pair, and the list of articles stored in ArrayList
    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";


    protected abstract void onCreate();

    abstract class DownloadNews extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        abstract protected String doInBackground(String... args);

        abstract protected void onPostExecute(String xml);

    }
}
