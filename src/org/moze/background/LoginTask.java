package org.moze.background;

import java.io.IOException;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;
import org.moze.background.ajax.HttpConnectionWithArgs;
import org.moze.constant.fxbtc.OperateConstants;

import android.os.AsyncTask;

public class LoginTask extends AsyncTask<String, Integer, String> {


	private int mErrorCode = 0;
	private String mErrorMsg = "";
	private onLoginStateChangeListener mListener;


	@Override
	protected void onPreExecute() {
		mListener.onLoginStart();
	}
	
	@Override
	protected void onPostExecute(String result) {
		if (result != "") {
			mListener.onLoginSuccess(result);
		} else {
			mListener.onLoginError(mErrorCode,mErrorMsg);
		}
	}

	@Override
	protected String doInBackground(String... params) {
		String token = "";
		try {
			String content = OperateConstants.OPERATION + "=" + OperateConstants.GET_TOKEN + "&"
					+ OperateConstants.USER_NAME + "=" + URLEncoder.encode(params[0], "UTF-8") + "&"
					+ OperateConstants.PASSWORD + "=" + URLEncoder.encode(params[1], "UTF-8");

			String result = HttpConnectionWithArgs.doPost(OperateConstants.LOGIN_QUEST, content);
			
			JSONObject tokenResult = new JSONObject(result);
			if (!result.equals("") && tokenResult.getBoolean("result")) {
				token = tokenResult.getString(OperateConstants.TOKEN);
				TraderApp.setUserName(params[0]);;
			} else {
				JSONObject errorInfo = tokenResult.getJSONObject(OperateConstants.ERROR);
				mErrorCode = errorInfo.getInt("code");
				mErrorMsg = errorInfo.getString("msg");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}
	
	public void setOnLoginStateChangeListener(onLoginStateChangeListener litener) {
		mListener = litener;
	}

	public interface onLoginStateChangeListener {
		public void onLoginStart();
		public void onLoginSuccess(String token);
		public void onLoginError(int errorCode , String msg);
	}
	
}
