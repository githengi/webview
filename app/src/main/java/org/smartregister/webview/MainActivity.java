package org.smartregister.webview;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    private FileHTTPServer fileHTTPServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            fileHTTPServer = new FileHTTPServer(this, null);
            fileHTTPServer.start();
        } catch (IOException e) {
            Timber.e(e);
        }

        WebView webView = findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl("http://localhost:8085/index.html");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileHTTPServer.destroy();
    }
}