package com.example.newsappv3;

// Stores API information, creates objects to store JSON information for each article
// Storing attribute data for each article in HashMap<String,String>
// Each key stores JSON value

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends NewsDriver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        listNews = findViewById(R.id.listNews);
        loader = findViewById(R.id.loader);
        listNews.setEmptyView(loader);


        //set up the bottom nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home as the selected item
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Listen for bottom nav bar activity
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.weather:
                        startActivity(new Intent(getApplicationContext(), Weather.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        // Check if network is available
        if (SharedResources.isNetworkAvailable(getApplicationContext())) {
            // If the network is available download the news
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText(getApplicationContext(), "Unable to connect to NewsAPI!", Toast.LENGTH_LONG).show();
        }
    }

    // Get the top headlines and fill in the article previews
    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        // Perform a Get on the Given URL
        protected String doInBackground(String... args) {
            String json = SharedResources.excuteGet("https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY);
            return json;
        }

        // Parse the data from the json and populate the article preview layout
        @Override
        protected void onPostExecute(String json) {

            // Check that articles were received
            if (json.length() > 0) {

                try {
                    JSONObject jsonResponse = new JSONObject(json);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                    // Save the Json data into a hash map
                    // Uses .optString to get data from the Json based on a specified key
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
                SharedResources.ArticlePreview adapter = new SharedResources.ArticlePreview(MainActivity.this, dataList);
                listNews.setAdapter(adapter);

                // Listener for Article clicks
                listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, DisplayFullArticle.class);
                        i.putExtra("url", dataList.get(+position).get(KEY_URL)); // Pass the Full article URL
                        startActivity(i);
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Unable to find any articles!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
