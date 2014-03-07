package org.moze.background.fxbtc;

import android.app.Application;

public class TraderApp extends Application {
	
	private static String mToken = "";
	private static String mUserName = "";
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	public static void setToken(String token) {
		mToken = token;
	}
	
	public static String getToken() {
		return mToken;
	}

	public static void setUserName(String name) {
		mUserName = name;
	}
	
	public static String getUserName() {
		return mUserName;
	}
	
}
