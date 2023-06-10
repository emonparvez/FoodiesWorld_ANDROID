package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Website extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        Intent i= getIntent();
        String website_str = i.getStringExtra("website");
        String restaurantName_str = i.getStringExtra("restaurantName");

        if(website_str.equals("") || website_str==null){
            webView.loadUrl("http://www.google.com/search?q="+restaurantName_str);
        }
        else{
            webView.loadUrl(website_str);
        }



    }

}
