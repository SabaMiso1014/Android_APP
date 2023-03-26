package com.example.yahoojapan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   //定形文
        setContentView(R.layout.activity_main);   //定形文

        // WebViewでYahooを表示する記述
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://www.yahoo.co.jp");

        // タップした時にブラウザを起動しないようにする処理
        myWebView.setWebViewClient(new WebViewClient());

        // JavaScriptを有効にする処理
        myWebView.getSettings().setJavaScriptEnabled(true);


        // Yahooに戻るボタンの記述
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebView myWebView = (WebView) findViewById(R.id.webview);
                myWebView.loadUrl("https://www.yahoo.co.jp");
            }
        });
    }
}