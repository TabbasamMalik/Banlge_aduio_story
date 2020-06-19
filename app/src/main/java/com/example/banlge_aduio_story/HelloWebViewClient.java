package com.example.banlge_aduio_story;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HelloWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // This line right here is what you're missing.
        // Use the url provided in the method.  It will match the member URL!
        view.loadUrl(url);
        return true;
    }
}
