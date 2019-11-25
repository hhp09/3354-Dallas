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

    // Storing each article in a HashMap as a key,value pair, and the list of articles stored in ArrayList
    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";


    abstract class DownloadNews extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        abstract protected String doInBackground(String... args);

        abstract protected void onPostExecute(String xml);

    }
}
