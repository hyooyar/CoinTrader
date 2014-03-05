package org.moze.background;

import java.lang.ref.WeakReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.moze.background.ajax.HttpConnectionWithArgs;
import org.moze.constant.fxbtc.OperateConstants;
import org.moze.constant.fxbtc.UrlConstants;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.Toast;

public class TradeTask extends AsyncTask<JSONObject, Integer, String> {
	
//	private Context mContext;
	private WeakReference<Context> mRef;
	
	public TradeTask(Context context) {
//		mContext = context;
		mRef=new WeakReference<Context>(context);
	}
	

	@Override
	protected void onPostExecute(String result) {
		Context context =mRef.get();
		if(context==null){
			return ;
		}else {
			if(result.equals("")){
				Toast.makeText(context, "挂单失败！", 250).show();
			} else {
				Toast.makeText(context, result, 250).show();
			}			
		}
	}

	@Override
	protected String doInBackground(JSONObject... params) {
		String info = "";
		try {

			String content = OperateConstants.OPERATION + "=" + OperateConstants.TRADE + "&"
					+ OperateConstants.TOKEN + "=" + TraderApp.getToken() + "&"
					+ UrlConstants.SYMBOL + "=" + params[0].getString(UrlConstants.SYMBOL) + "&"
					+ OperateConstants.TYPE + "=" + params[0].getString(OperateConstants.TYPE) + "&"
					+ OperateConstants.RATE + "=" + params[0].getString(OperateConstants.RATE) + "&"
					+ OperateConstants.VOL + "=" + params[0].getString(OperateConstants.VOL);

			String result = HttpConnectionWithArgs.doPost(OperateConstants.LOGIN_QUEST, content);
			JSONObject tradeInfo = new JSONObject(result).getJSONObject("trade_info");
			JSONArray tradedOrder = tradeInfo.getJSONArray("traded_orders");
			for(int i = 0; i < tradedOrder.length() ; i++){
				JSONObject orderInfo = tradedOrder.getJSONObject(i);
				String vol = orderInfo.getString(OperateConstants.VOL);
				String rate = orderInfo.getString(OperateConstants.RATE);
				info += "已成交！成交量：" + vol + "，成交价：" + rate + "\n";
			}
			JSONArray limitedOrder = tradeInfo.getJSONArray("limit_orders");
			for(int i = 0; i < limitedOrder.length() ; i++){
				JSONObject orderInfo = limitedOrder.getJSONObject(i);
				String vol = orderInfo.getString(OperateConstants.VOL);
				String rate = orderInfo.getString(OperateConstants.RATE);
				info += "已挂单！挂单量：" + vol + "，挂单价：" + rate;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return info;
	}

}
