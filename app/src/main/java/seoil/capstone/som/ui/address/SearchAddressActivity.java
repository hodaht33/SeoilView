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

// 다음 주소 api 이용을 위한 웹뷰 액티비티
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

        // 설정
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.addJavascriptInterface(new AndroidBridge(), "SOM");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("https://leebera.name/api/daum-address");
    }

    // 검색한 결과의 주소 클릭 시 액티비티 종료하며 누른 주소의 정보를 가지고 돌아감
    private class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    intent.putExtra("PostalCode", arg1);
                    intent.putExtra("Address", arg2);
                    intent.putExtra("BuildingName", arg3);

                    setResult(2,intent);

                    finish();
                }
            });
        }
    }
}