package com.example.newsappv3;

// DisplayFullActivity will display the full article page in WebView. As well as set up a toolbar with an options menu allowing users to share and save the article

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class DisplayFullArticle extends AppCompatActivity {

    WebView webView;
    ProgressBar loader;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_full_article);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

        // Load the full article
        webView.loadUrl(url);

    }
    //method to show menu of options
    public void showMenu(View v) {
        PopupMenu optionMenu = new PopupMenu(this, v);
        optionMenu.inflate(R.menu.menu_options);
        optionMenu.show();
    }

}