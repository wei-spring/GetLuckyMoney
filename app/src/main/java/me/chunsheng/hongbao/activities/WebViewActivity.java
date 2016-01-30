package me.chunsheng.hongbao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.chunsheng.hongbao.R;

public class WebViewActivity extends Activity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl("http://h-notes.com/notes/FN5phh15lR9dVJNZ/20160130/NBZttBFdhJN9htVxVNZdBJtZtRxRxx11hp5plFBx5J955Fx5R5VNhRt91pZZpNx1/index.html");
    }

    public void performBack(View view) {
        super.onBackPressed();
    }
}
