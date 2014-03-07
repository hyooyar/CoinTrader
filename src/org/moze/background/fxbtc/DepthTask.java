package org.moze.background.fxbtc;

import java.util.ArrayList;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.moze.background.ajax.HttpConnectionWithArgs;
import org.moze.background.fxbtc.datatype.DepthOrder;
import org.moze.background.fxbtc.datatype.Order;
import org.moze.constant.fxbtc.UrlConstants;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DepthTask extends TimerTask {
	
	private String mDepthUrl = "";
	private Handler mHandler;
	private DepthOrder mDepthOrder;
	
	public DepthTask(String url , Handler handler) {
		mDepthUrl = url;
		mHandler = handler;
	}

	@Override
	public void run() {
		Log.d("Depth", "Fresh!");
		String resultData = "";
		try {
            resultData = HttpConnectionWithArgs.doGet(mDepthUrl);
            ArrayList<Order> askOrders = new ArrayList<Order>();
            ArrayList<Order> bidOrders = new ArrayList<Order>();
            JSONObject depthResult = new JSONObject(resultData);
			if(!depthResult.getBoolean("result")){
				return;
			} else {
				JSONObject depth = depthResult.getJSONObject("depth");
				JSONArray asks = depth.getJSONArray("asks");
				int size = 4;
				if(asks.length() < 4){
					size = asks.length();
				}
				for(int i = size; i >= 0; i--){
					JSONObject askOrder = asks.getJSONObject(i);
					int count = askOrder.getInt("count");
					double rate = askOrder.getDouble("rate");
					double vol = askOrder.getDouble("vol");
					Order order = new Order(count, rate, vol);
					askOrders.add(order);
				}
				JSONArray bids = depth.getJSONArray("bids");
				for(int i = 0; i < bids.length(); i++){
					JSONObject bidOrder = bids.getJSONObject(i);
					int count = bidOrder.getInt("count");
					double rate = bidOrder.getDouble("rate");
					double vol = bidOrder.getDouble("vol");
					Order order = new Order(count, rate, vol);
					bidOrders.add(order);
				}
				mDepthOrder = new DepthOrder(askOrders, bidOrders);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mHandler.removeMessages(UrlConstants.DEPTH_MESSAGE);
		Message msg = new Message();
		msg.what = UrlConstants.DEPTH_MESSAGE;
		msg.obj = mDepthOrder;
		mHandler.sendMessage(msg);
	}
}
