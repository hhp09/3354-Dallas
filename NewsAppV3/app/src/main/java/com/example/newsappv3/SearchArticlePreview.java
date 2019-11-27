package com.example.newsappv3;

// Stores API information, creates objects to store JSON information for each article
// Storing attribute data for each article in HashMap<String,String>
// Each key stores JSON value

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class SearchArticlePreview extends NewsDriver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_article_preview);

        setTitle("");

        // Get the keyword entered by the user
        keyWord = getIntent().getExtras().getString("keyWord");
        listNews = findViewById(R.id.listNews);
        loader = findViewById(R.id.loader);
        listNews.setEmptyView(loader);

        // Download news if network is available
        if (SharedResources.isNetworkAvailable(getApplicationContext())) {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        // Set the bottom nav bar view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Search as the selected activity
        bottomNavigationView.setSelectedItemId(R.id.search);

        // Listen for bottom navigation usage
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext(), Save.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    // Download the news articles
    class DownloadNews extends AsyncTask<String, Void, String> {

        // Perform a Get on the Given URL
        protected String doInBackground(String... args) {
            String xml = SharedResources.excuteGet("https://newsapi.org/v2/everything?" + "q=" + keyWord + "&apiKey=" + API_KEY);
            return xml;
        }

        // Parse the Json data and save the json data into a HashMap
        // Use the Hash map to fill in the article preview layout
        @Override
        protected void onPostExecute(String xml) {

            // Check that articles were received
            if (xml.length() > 0) {

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                    // Save the Json data into a hash map
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        JSONObject source = jsonObject.getJSONObject(KEY_SOURCE);
                        map.put(KEY_NAME, source.getString(KEY_NAME));
                        map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE));
                        map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION));
                        map.put(KEY_URL, jsonObject.optString(KEY_URL));
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE));
                        map.put(KEY_PUBLISHEDAT, SharedResources.DateFormat(jsonObject.optString(KEY_PUBLISHEDAT)));
                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
                }

                // Populate the Article preview view
                SharedResources.ArticlePreview adapter = new SharedResources.ArticlePreview(SearchArticlePreview.this, dataList);
                listNews.setAdapter(adapter);

                // Listener for Article clicks.
                listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Show the full article that user selects
                        Intent i = new Intent(SearchArticlePreview.this, DisplayFullArticle.class);
                        i.putExtra("url", dataList.get(+position).get(KEY_URL));
                        startActivity(i);
                    }
                });

            } else {
                Toast errorMsg = Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT);
                errorMsg.setGravity(Gravity.CENTER, 0, 0);
                errorMsg.show();
                startActivity(new Intent(getApplicationContext(), Search.class));
                overridePendingTransition(0, 0);
            }
        }
    }
}
