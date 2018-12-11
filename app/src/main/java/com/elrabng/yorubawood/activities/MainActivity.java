package com.elrabng.yorubawood.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.elrabng.yorubawood.R;

public class MainActivity extends AppCompatActivity {
    WebView web;
    ProgressDialog progress_loader;
    String website;
    private final String TAG = "getting";
    private final String URL = "http://www.yorubawood.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        reload();
    }

    private void reload() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 1), (int) (height * 1));
        Log.i(TAG, "the url " + website);
        web = findViewById(R.id.wv);

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.getSettings().setDomStorageEnabled(true);
        web.loadUrl(URL);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());

        web.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            // This method will be triggered when the Page loading is complete
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            // This method will be triggered when error page appear
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                progress_loader.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(MainActivity.this,
                        "The Requested Page Does Not Exist" + website, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        reload();
    }
}