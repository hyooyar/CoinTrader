package org.moze.background.fxbtc;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.moze.background.ajax.HttpConnectionWithArgs;
import org.moze.background.fxbtc.datatype.UserOrder;
import org.moze.constant.fxbtc.OperateConstants;
import org.moze.constant.fxbtc.UrlConstants;

import android.os.AsyncTask;

public class UserOrdersTask extends AsyncTask<String, Integer, ArrayList<UserOrder>> {

	@Override
	protected void onPostExecute(ArrayList<UserOrder> result) {
		mListener.onUserOrderUpdate(result);
	}

	private onUserOrdersUpdateListener mListener;
	
	@Override
	protected ArrayList<UserOrder> doInBackground(String... params) {
		ArrayList<UserOrder> userOrders = new ArrayList<UserOrder>();
		try{
			String content = OperateConstants.OPERATION + "=" + OperateConstants.GET_ORDERS + "&"
					+ OperateConstants.TOKEN + "=" + TraderApp.getToken() + "&"
					+ UrlConstants.SYMBOL + "=" + params[0];
			String result = HttpConnectionWithArgs.doPost(OperateConstants.LOGIN_QUEST, content);
			JSONObject userInfo = new JSONObject(result);
			JSONArray userOrderData = userInfo.getJSONArray("orders");
			for(int i = 0; i < userOrderData.length() ; i++){
				JSONObject orderInfo = userOrderData.getJSONObject(i);
				UserOrder order = new UserOrder();
				order.setId(orderInfo.getLong("id"));
				order.setType(orderInfo.getString("type"));
				order.setSymbol(params[0]);
				order.setRate(orderInfo.getDouble("rate"));
				order.setVol(orderInfo.getDouble("vol"));
				userOrders.add(order);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userOrders;
	}

	public void setOnUserOrdersUpdateLitsener(onUserOrdersUpdateListener listener){
		mListener = listener;
	}
	
	public interface onUserOrdersUpdateListener {
		
		public void onUserOrderUpdate(ArrayList<UserOrder> orders);
		
	}
	
}
