package com.example.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_webview);

            WebView web = findViewById(R.id.web);
            web.loadUrl("https://www.aparat.com");
            web.getSettings().setJavaScriptEnabled(true);
            web.setWebViewClient(new WebViewClient());
        } catch (Exception ex) {
            Toast.makeText(WebviewActivity.this,
                    ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}