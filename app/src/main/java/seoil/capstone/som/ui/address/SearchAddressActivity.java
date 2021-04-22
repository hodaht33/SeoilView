package seoil.capstone.som.ui.address;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import seoil.capstone.som.R;

public class SearchAddressActivity extends AppCompatActivity {

    private WebView mWebView;
    private Handler mHandler;
    private Intent intent;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);

        intent = new Intent();
        mHandler = new Handler();

        mWebView = findViewById(R.id.webViewSearchAddress);
        //설정
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.addJavascriptInterface(new AndroidBridge(), "SOM");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("http://192.168.0.13/daumpostcode/getAddress.html");
    }

    private class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    intent.putExtra("1", arg1);
                    Log.d("putExtra",arg1);
                    intent.putExtra("2", arg2);
                    Log.d("putExtra",arg2);
                    intent.putExtra("3", arg3);
                    Log.d("putExtra",arg3);
                    setResult(2,intent);
                    finish();
                }
            });
        }
    }
}