package org.moze.view;

import java.util.ArrayList;

import org.moze.background.fxbtc.datatype.Order;
import org.moze.constant.fxbtc.UrlConstants;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	
	private ArrayList<Order> mOrderList = new ArrayList<Order>();
	private Context mContext;
	private String mTag = "";
	
	public OrderAdapter(ArrayList<Order> orderList , Context context , String tag) {
		mOrderList = orderList;
		mContext = context;
		mTag = tag;
	}

	@Override
	public int getCount() {
		return mOrderList.size() > 5 ? 5 : mOrderList.size();
	}

	@Override
	public Object getItem(int position) {
		return mOrderList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.order_item, null);
			holder.mCountText = (TextView) convertView.findViewById(R.id.order_count);
			holder.mRateText = (TextView) convertView.findViewById(R.id.order_rate);
			holder.mVolText = (TextView) convertView.findViewById(R.id.order_vol);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}

		String count = "<font color='#aabb00'>单数：</font>" + mOrderList.get(position).getCount();
		String rate = "" + mOrderList.get(position).getRate();
		String vol = "" + mOrderList.get(position).getVol();
		if(mTag.equals(UrlConstants.ASK)){
			rate = "<font color='#c13936'>卖出价：</font>" + rate;
			vol = "<font color='#c13936'>卖出量：</font>" + vol;
		} else if(mTag.equals(UrlConstants.BID)){
			rate = "<font color='#008c00'>买入价：</font>" + rate;
			vol = "<font color='#008c00'>买入量：</font>" + vol;
		}
		holder.mCountText.setText(Html.fromHtml(count));
		holder.mCountText.setVisibility(View.GONE);
		holder.mRateText.setText(Html.fromHtml(rate));
		holder.mVolText.setText(Html.fromHtml(vol));
		
		return convertView;
	}
	
	static class ViewHolder {
		private TextView mCountText;
		private TextView mRateText;
		private TextView mVolText;
	};

}
