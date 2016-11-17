package com.mx.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.mx.util.PayOrder;

public class MXPay {
	private static MXPay pay;
	static final String TAG = "MXPay";
	static final String CID = "01";
	

	public static MXPay getInstance() {
		if (pay == null) {
			pay = new MXPay();

		}
		return pay;
	}

	public void doPay(PayOrder payOrder, Activity activity, MXPayCallBack callBack) {

		if (callBack == null) {
			return;
		}
		PayResult result = new PayResult();
		result.setResultCode(-1);

		if (payOrder == null || payOrder.getOrderInfo().isEmpty()) {
			result.setResultDesc("payOrder is null or OrderNo is empty");
			callBack.onReceiveCallBack(result);
			return;
		}

		if (activity == null) {
			result.setResultDesc("activity is null");
			callBack.onReceiveCallBack(result);
			return;
		}

		cmbActivity.mCallBack = callBack;
		cmbActivity.mOrder = payOrder;

		Intent intent = new Intent(activity, cmbActivity.class);

//		Bundle bundle = new Bundle();
//		final String postDate = payOrder.getOrderInfo();
//
//		bundle.putString("postDate", postDate);
//		intent.putExtras(bundle);

		activity.startActivity(intent);

	}

	public interface MXPayCallBack {
		public void onReceiveCallBack(PayResult result);
	}
	
}
