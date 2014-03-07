package org.moze.background.fxbtc;

import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.moze.background.ajax.HttpConnectionWithArgs;
import org.moze.constant.fxbtc.UrlConstants;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class TickerTask extends TimerTask {
	
	private String mTickerUrl = "";
	private Handler mHandler;
	
	public TickerTask(String url , Handler handler) {
		mTickerUrl = url;
		mHandler = handler;
	}

	@Override
	public void run() {
		Log.d("Ticker", "Fresh!");
		String resultData = "";
		try {
            resultData = HttpConnectionWithArgs.doGet(mTickerUrl);
            JSONObject tickerResult = new JSONObject(resultData);
			if(!tickerResult.getBoolean("result")){
				return;
			} else {
				JSONObject params = tickerResult.getJSONObject("params");
				resultData = "<font color='#ff0000'>" + params.getString("symbol").toUpperCase() + "</font>\t\t";
				JSONObject ticker = tickerResult.getJSONObject("ticker");
				resultData += "最高价：<font color='#00b000'>" + ticker.getDouble("high") + "</font>\t\t";
				resultData += "最低价：<font color='#00b000'>" + ticker.getDouble("low") + "</font><br/>";
				resultData += "最新价：<font color='#00b000'>" + ticker.getDouble("last_rate") + "</font>\t\t";
				resultData += "交易总量：<font color='#00b000'>" + ticker.getInt("vol") + "</font>";
//				resultData += "最低卖价：<font color='#00b000'>" + ticker.getDouble("ask") + "</font>\t\t";
//				resultData += "最高买价：<font color='#00b000'>" + ticker.getDouble("bid") + "</font>";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Message msg = new Message();
		msg.what = UrlConstants.TICKER_MESSAGE;
		msg.obj = resultData;
		mHandler.sendMessage(msg);
	}

}
