package com.elrabng.yorubawood.activities;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.elrabng.yorubawood.R;

public class MainActivity extends Activity {
    WebView web;
    ProgressDialog progress_loader;
    String website;
    private final String TAG = "getting";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//to fit the resolution size of the phone

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width *1 ),(int)(height*1 ));
//        Intent intent = getIntent();
//        website = intent.getStringExtra("url");
        Log.i(TAG,"the url "+website);
        web = findViewById(R.id.wv);


        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        //webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        web.getSettings().setDomStorageEnabled(true);
        // webSettings.setDatabaseEnabled(true);
        web.loadUrl("http://www.yorubawood.com/");
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());

        web.setWebViewClient(new WebViewClient() {
// This method will be triggered when the Page Started Loading

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progress_loader = ProgressDialog.show(MainActivity.this, null,
                        "Please Wait...");
                progress_loader.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }
// This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                progress_loader.dismiss();
                super.onPageFinished(view, url);
            }

// This method will be triggered when error page appear
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                progress_loader.dismiss();
// You can redirect to your own page instead getting the default
// error page
                Toast.makeText(MainActivity.this,
                        "The Requested Page Does Not Exist"+website, Toast.LENGTH_SHORT).show();
//
//                Intent back_intent = new Intent(getApplicationContext(),Education.class);
//                back_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        });




    }
}
