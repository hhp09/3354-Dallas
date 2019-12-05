package com.example.newsappv3;

// DisplayFullActivity will display the full article page in WebView. As well as set up a toolbar with an options menu allowing users to share and save the article

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class DisplayFullArticle extends AppCompatActivity {

    WebView webView;
    ProgressBar loader;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_full_article);
        //sets the toolbar at the top of screen
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //sets up a back button that returns user to previous activity
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        setTitle("");

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        loader = findViewById(R.id.loader);
        webView = findViewById(R.id.webView);

        // Set up for web view
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        // Set up for the web view client
        webView.setWebViewClient(new WebViewClient() {

            @Override
            //Gives the host application a chance to take control when a URL is about to be loaded in the current WebView
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loader.setVisibility(View.VISIBLE);
                view.loadUrl(url);

                return true;
            }

            @Override
            // Notifies the host application that a page has finished loading.
            public void onPageFinished(WebView view, final String url) {
                loader.setVisibility(View.GONE);
            }
        });

        // Load the full article in the web view
        webView.loadUrl(url);

    }
    //method to show menu of options
    public void showMenu(View v) {

        //Sets up the click listener for when the user selects an option
        final PopupMenu optionMenu = new PopupMenu(this, v);
        optionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    //when share is tapped call the share method
                    case R.id.option_share:
                        shareUrl();
                        return true;
                    //When save is tapped call share method
                    case R.id.option_save:
                        return true;
                }
                return false;
            }
        });
        //Inflate to add the drop down menu to the toolbar
        optionMenu.inflate(R.menu.menu_options);
        optionMenu.show();




    }
    //opens up the share menu to share url
    public void shareUrl(){
        Intent sendIntent = new Intent();
        //pulls up the sharing menu
        sendIntent.setAction(Intent.ACTION_SEND);
        //loads what information is to be shared
        sendIntent.putExtra(Intent.EXTRA_TEXT, url);
        //sets the MIME data of what is being shared
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

    }


}

