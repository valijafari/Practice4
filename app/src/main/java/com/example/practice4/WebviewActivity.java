package com.example.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView web = findViewById(R.id.web);
        web.loadUrl("https://www.aparat.com");
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
    }
}