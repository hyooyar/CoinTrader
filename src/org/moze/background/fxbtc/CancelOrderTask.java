package org.moze.background.fxbtc;

import org.json.JSONException;
import org.json.JSONObject;
import org.moze.background.ajax.HttpConnectionWithArgs;
import org.moze.background.fxbtc.datatype.UserOrder;
import org.moze.constant.fxbtc.OperateConstants;
import org.moze.constant.fxbtc.UrlConstants;
import org.moze.view.UserOrdersAdapter;

import android.os.AsyncTask;

public class CancelOrderTask extends AsyncTask<UserOrder, Integer, UserOrder> {
	private UserOrdersAdapter mAdapter;

	@Override
	protected void onPostExecute(UserOrder result) {
		if(result != null){
			mAdapter.deleteOrder(result);
		}
	}

	@Override
	protected UserOrder doInBackground(UserOrder... order) {
		String content = OperateConstants.OPERATION + "=" + OperateConstants.CANCEL_ORDER
				+ "&" + OperateConstants.TOKEN + "=" + TraderApp.getToken()
				+ "&" + UrlConstants.SYMBOL + "=" + order[0].getSymbol()
				+ "&" + OperateConstants.ID + "=" + order[0].getId();
		String result = HttpConnectionWithArgs.doPost(OperateConstants.LOGIN_QUEST, content);
		try {
			JSONObject userInfo = new JSONObject(result);
			if (!result.equals("") && userInfo.getBoolean("result")) {
				if(order[0].getId() == userInfo.getLong("id")){
					return order[0];
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void setOrderAdapter(UserOrdersAdapter adapter) {
		mAdapter = adapter;
	}

}
