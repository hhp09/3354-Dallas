package com.example.newsappv3;

// Abstract class used by any class intending to make a call to the News API

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class NewsDriver extends AppCompatActivity {

    String API_KEY = "47da75493074479c95323798e6a853ea"; // secret key
    String keyWord;
    ListView listNews;
    ProgressBar loader;

    // Strings used to parse data from a json file.
    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_SOURCE = "source";
    static final String KEY_NAME = "name";
    static final String KEY_PUBLISHEDAT = "publishedAt";

    // Methods needed to properly call the News API and save the data correctly.
    abstract class DownloadNews extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        abstract protected String doInBackground(String... args);

        abstract protected void onPostExecute(String json);

    }
}
