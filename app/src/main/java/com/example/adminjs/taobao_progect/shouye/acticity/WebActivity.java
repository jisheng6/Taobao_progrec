package com.example.adminjs.taobao_progect.shouye.acticity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.adminjs.taobao_progect.R;

public class WebActivity extends Activity{
    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.web_layout);

    WebView webView = (WebView) findViewById(R.id.web_view);

    Intent intent = getIntent();
    String url = intent.getStringExtra("url");

    //加载
    webView.loadUrl(url);

    //设置
    webView.setWebViewClient(new WebViewClient());

    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setJavaScriptCanOpenWindowsAutomatically(true);
}
}