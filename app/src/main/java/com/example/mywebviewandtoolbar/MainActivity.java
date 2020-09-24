package com.example.mywebviewandtoolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ProgressBar progressBar=findViewById(R.id.progress);
        WebView webView=findViewById(R.id.webview);
        WebViewClient webViewClient=new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("PAGE STARTED");
                progressBar.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("PAGE FINISHED");
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);


            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url=request.getUrl().toString();
                System.out.println("shouldOverrideUrlLoading" + request.getUrl().toString());
                if (!url.contains("islamweb.net")){
                    Toast.makeText(MainActivity.this, "You are trying to leave this website!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                System.out.println("shouldInterceptRequest" + request.getUrl().toString());
                return super.shouldInterceptRequest(view, request);
            }
        };

        webView.setWebViewClient(webViewClient);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.islamweb.net");
    }
}