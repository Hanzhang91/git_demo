package com.example.internettype;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	public String NET_TYPE_INVALID = "设备无联网";
	public String NET_TYPE_WIFI = "设备wifi联网";
	public String NET_TYPE_CMNET = "中国移动cmnet联网";
	public String NET_TYPE_CMWAP = "中国移动cmwap联网";
	public String NET_TYPE_UNIWAP = "中国联通uniwap联网";
	public String NET_TYPE_UNINET = "中国联通uninet联网";
	public String NET_TYPE_3G_CMWAP = "中国移动3gwap联网";
	public String NET_TYPE_3G_CMNET = "中国移动3gnet联网";
	public String NET_TYPE_3G_UNINET = "中国联通3gnet联网";
	public String NET_TYPE_3G_UNIWAP = "中国联通3gwap联网";
	public String NET_TYPE_CTWAP = "中国电信ctwap联网";
	public String NET_TYPE_CTNET = "中国电信ctnet联网";
	public TextView textView_internettype;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	public String getInternetType(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = null;
		try {
			activeNetInfo = connectivityManager.getActiveNetworkInfo();
		} catch (Exception e) {
			e.printStackTrace();
			return NET_TYPE_INVALID;
		}
		if (activeNetInfo == null)
			return NET_TYPE_INVALID;

		if (activeNetInfo.getState() == NetworkInfo.State.CONNECTED) {
			if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				return NET_TYPE_WIFI;
			} else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				String apn = activeNetInfo.getExtraInfo();// .toLowerCase();
				if (apn == null || "".equals(apn)) {
					return NET_TYPE_CMNET;
				}
				apn = apn.toLowerCase();
				if (apn.equals("cmwap")) {
					return NET_TYPE_CMWAP;
				} else if (apn.equals("cmnet")) {
					return NET_TYPE_CMNET;
				} else if (apn.equals("3gwap")) {
					return NET_TYPE_3G_CMWAP;
				} else if (apn.equals("3gnet")) {
					return NET_TYPE_3G_CMNET;
				} else if (apn.equals("uniwap")) {
					return NET_TYPE_UNIWAP;
				} else if (apn.equals("uninet")) {
					return NET_TYPE_UNINET;
				} else if (apn.equals("ctwap")) { // 4.0上电信3g卡ctwap不用代理也能连接
					return NET_TYPE_CTWAP;
				} else if (apn.equals("ctnet") || apn.equals("#777")) {
					return NET_TYPE_CTNET;
				} else {
					return NET_TYPE_CMNET;
				}
			} else {
				return NET_TYPE_CMNET;
			}
		} else {
			NetworkInfo[] netInfos = connectivityManager.getAllNetworkInfo();
			if (netInfos == null) {
				return NET_TYPE_INVALID;
			}
			int len = netInfos.length;
			for (int i = 0; i < len; i++) {
				if (netInfos[i].getState() == NetworkInfo.State.CONNECTED
						&& ConnectivityManager.TYPE_MOBILE == netInfos[i]
								.getType()) {
					String apn = activeNetInfo.getExtraInfo();// .toLowerCase();
					if (apn == null || "".equals(apn)) {
						return NET_TYPE_CMNET;
					}
					apn = apn.toLowerCase();
					if (apn.equals("cmwap")) {
						return NET_TYPE_CMWAP;
					} else if (apn.equals("cmnet")) {
						return NET_TYPE_CMNET;
					} else if (apn.equals("3gwap")) {
						return NET_TYPE_3G_CMWAP;
					} else if (apn.equals("3gnet")) {
						return NET_TYPE_3G_CMNET;
					} else if (apn.equals("uniwap")) {
						return NET_TYPE_UNIWAP;
					} else if (apn.equals("uninet")) {
						return NET_TYPE_UNINET;
					} else if (apn.equals("ctwap")) { // 4.0上电信3g卡ctwap不用代理也能连接
						return NET_TYPE_CTWAP;
					} else if (apn.equals("ctnet") || apn.equals("#777")) {
						return NET_TYPE_CTNET;
					} else {
						return NET_TYPE_CMNET;
					}
				}
			}
			return NET_TYPE_INVALID;
		}
	}

	public void init() {
		textView_internettype = (TextView) this
				.findViewById(R.id.internet_type);
		textView_internettype.setText(getInternetType(getApplicationContext()));
	}
}
