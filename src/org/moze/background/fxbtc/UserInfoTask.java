package org.moze.background.fxbtc;

import org.json.JSONException;
import org.json.JSONObject;
import org.moze.background.ajax.HttpConnectionWithArgs;
import org.moze.constant.fxbtc.OperateConstants;
import org.moze.constant.fxbtc.UrlConstants;

import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

public class UserInfoTask extends AsyncTask<String, Integer, String> {

	private TextView mUserInfoOverview;
	
	@Override
	protected void onPostExecute(String result) {
		mUserInfoOverview.setText(Html.fromHtml(result));
		cancel(true);
	}

	@Override
	protected String doInBackground(String... params) {
		String userInfoText = "ª∂”≠ªÿ¿¥£¨" + TraderApp.getUserName() + "£°<br/>";
		try{
			String content = OperateConstants.OPERATION + "=" + OperateConstants.GET_INFO
					+ "&" + OperateConstants.TOKEN + "=" + params[0];
			String result = HttpConnectionWithArgs.doPost(OperateConstants.LOGIN_QUEST, content);
			JSONObject userInfo = new JSONObject(result);
			if (!result.equals("") && userInfo.getBoolean("result")) {
				JSONObject info = userInfo.getJSONObject("info");
				JSONObject funds = info.getJSONObject("funds");
				JSONObject freeFunds = funds.getJSONObject("free");
				userInfoText += "<font color='#ff7722'>BTC</font>£∫"
						+ UrlConstants.FUND_FORMAT.format(freeFunds.getDouble(UrlConstants.BTC)) + "<br/>";
				userInfoText += "<font color='#666666'>LTC</font>£∫"
						+ UrlConstants.FUND_FORMAT.format(freeFunds.getDouble(UrlConstants.LTC)) + "<br/>";
				userInfoText += "<font color='#22bb00'>CNY</font>£∫"
						+ UrlConstants.FUND_FORMAT.format(freeFunds.getDouble(UrlConstants.CNY));
			} else {
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userInfoText;
	}

	public void setUserInfoTextView(TextView textView) {
		mUserInfoOverview = textView;		
	}

}
