package com.example.newsappv3;

// Contains functions and classes that are common in both the Main Activity and Search Activity

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SharedResources {

    // Check in network is available
    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    // Call news endpoint and save the response
    public static String excuteGet(String targetURL) {
        URL url;
        HttpURLConnection connection = null;
        try {
            // Make the http connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("content-type", "application/json;  charset=utf-8");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);

            InputStream input;

            // Check that connection was made properly
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK)
                input = connection.getErrorStream();
            else
                // if connection was successful then save data
                input = connection.getInputStream();

            // Save each article from the input stream into a StringBuffer
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            reader.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // Reformat a given date
    public static String DateFormat(String oldstringDate){
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }

    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

    // Shared class used to display preview articles
    public static class ArticlePreview extends BaseAdapter {
        private Activity activity;
        private ArrayList<HashMap<String, String>> data;

        public ArticlePreview(Activity a, ArrayList<HashMap<String,String>> d) {
            activity = a;
            data = d;
        }

        public int getCount() {
            return data.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ListNewsViewHolder holder = null;
            if (convertView == null) {

                holder = new ListNewsViewHolder();
                convertView = LayoutInflater.from(activity).inflate(
                        R.layout.article_info, parent, false);
                holder.galleryImage = (ImageView) convertView.findViewById(R.id.galleryImage);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.details = (TextView) convertView.findViewById(R.id.details);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                convertView.setTag(holder);

            } else {
                holder = (ListNewsViewHolder) convertView.getTag();
            }

            holder.galleryImage.setId(position);
            holder.name.setId(position);
            holder.title.setId(position);
            holder.details.setId(position);
            holder.time.setId(position);

            HashMap<String, String> map = new HashMap<String, String>();
            map = data.get(position);

            // Populate the article list view
            try{

                holder.name.setText(map.get(MainActivity.KEY_NAME));
                holder.title.setText(map.get(MainActivity.KEY_TITLE));
                holder.time.setText(map.get(MainActivity.KEY_PUBLISHEDAT));
                holder.details.setText(map.get(MainActivity.KEY_DESCRIPTION));


                Picasso.get()
                        .load(map.get(MainActivity.KEY_URLTOIMAGE))
                        .resize(300, 200)
                        .centerCrop()
                        .into(holder.galleryImage);


            } catch(Exception e) {}
            return convertView;
        }
    }

    static class ListNewsViewHolder {
        ImageView galleryImage;
        TextView name, title, details, time;
    }
}

