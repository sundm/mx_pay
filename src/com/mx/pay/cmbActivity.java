package com.mx.pay;

import org.apache.http.util.EncodingUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cmb.pb.util.CMBKeyboardFunc;

import com.mx.pay.MXPay.MXPayCallBack;
import com.mx.util.PayOrder;

public class cmbActivity extends Activity {
	private String tagString = "http client";
	private WebView wvSite;
	public static MXPayCallBack mCallBack;
	public static PayOrder mOrder;

	@SuppressLint("SetJavaScriptEnabled")
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int layoutResID = getResources().getIdentifier("cmd_main", "layout", getPackageName());
		setContentView(layoutResID);// (R.layout.cmd_main);

		// Instanciation du WebView...
		int webId = getResources().getIdentifier("webview", "id", getPackageName());
		wvSite = (WebView) findViewById(webId);// (R.id.webview);
		// ...on active JavaScript...
		WebSettings webSettings = wvSite.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSaveFormData(false);
		webSettings.setSavePassword(false);
		webSettings.setSupportZoom(false);

		final String postDate = mOrder.getOrderInfo();

		wvSite.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.i(tagString, url);
				CMBKeyboardFunc kbFunc = new CMBKeyboardFunc(cmbActivity.this);
				if (url.equals("http://127.0.0.1/")) {
					PayResult result = new PayResult();
					result.setResultCode(0);
					result.setTradeId(mOrder.getTradeId());
					result.setTradeAmount(mOrder.getAmount());
					cmbActivity.mCallBack.onReceiveCallBack(result);
					cmbActivity.this.finish();
					return false;
				} else if (kbFunc.HandleUrlCall(wvSite, url) == false) {
					return super.shouldOverrideUrlLoading(view, url);
				} else {
					return true;
				}

			}
		});

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				wvSite.postUrl("http://61.144.248.29:801/netpayment/BaseHttp.dll?PrePayEUserP",
						EncodingUtils.getBytes(postDate, "BASE64"));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.webview, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			PayResult result = new PayResult();
			result.setResultCode(0);
			result.setTradeId(mOrder.getTradeId());
			result.setTradeAmount(mOrder.getAmount());
			cmbActivity.mCallBack.onReceiveCallBack(result);
			cmbActivity.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}