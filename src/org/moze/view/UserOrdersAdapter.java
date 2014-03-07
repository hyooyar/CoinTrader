package org.moze.view;

import java.util.ArrayList;

import org.moze.background.fxbtc.CancelOrderTask;
import org.moze.background.fxbtc.datatype.UserOrder;
import org.moze.constant.fxbtc.UrlConstants;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserOrdersAdapter extends BaseExpandableListAdapter{
	private static final int GROUP_SIZE = 3;
	private ArrayList<UserOrder> mUserBtcCnyOrders;
	private ArrayList<UserOrder> mUserLtcCnyOrders;
	private ArrayList<UserOrder> mUserLtcBtcOrders;
	private Context mContext;
	private UserOrdersAdapter mInstance;
	private CancelOrderTask mCancelTask;
	private OnOrderCancelListener mListener;
	
	public UserOrdersAdapter(Context context){
		mUserBtcCnyOrders = new ArrayList<UserOrder>();
		mUserLtcCnyOrders = new ArrayList<UserOrder>();
		mUserLtcBtcOrders = new ArrayList<UserOrder>();
		mContext = context;
		mInstance = this;
	}
	
	public void setOnOrderCancelListener(OnOrderCancelListener listener){
		mListener = listener;
	}

	public void setBtcCnyOrders(ArrayList<UserOrder> orders){
		mUserBtcCnyOrders = orders;
	}
	
	public void setLtcCnyOrders(ArrayList<UserOrder> orders){
		mUserLtcCnyOrders = orders;
	}
	
	public void setLtcBtcOrders(ArrayList<UserOrder> orders){
		mUserLtcBtcOrders = orders;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groupProjection(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHolder holder;
		if(convertView == null){
			holder = new ChildViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.order_item, null);
			holder.mTypeText = (TextView) convertView.findViewById(R.id.order_count);
			holder.mRateText = (TextView) convertView.findViewById(R.id.order_rate);
			holder.mVolText = (TextView) convertView.findViewById(R.id.order_vol);
			holder.mDeleteImage = (ImageView) convertView.findViewById(R.id.order_delete);
			convertView.setTag(holder);
		} else {
			holder = (ChildViewHolder)convertView.getTag();
		}
		ArrayList<UserOrder> orders = groupProjection(groupPosition);
		final UserOrder order = orders.get(childPosition);
		String type = order.getType();
		String rate = "" + order.getRate();
		String vol = "" + order.getVol();
		if(type.equals(UrlConstants.ASK)){
			type = "卖出";
			rate = "<font color='#aabb00'>卖出价：</font>" + rate;
			vol = "<font color='#aabb00'>卖出量：</font>" + vol;
		} else if(type.equals(UrlConstants.BID)){
			type = "买入";
			rate = "<font color='#aabb00'>买入价：</font>" + rate;
			vol = "<font color='#aabb00'>买入量：</font>" + vol;
		}
		type = "<font color='#aabb00'>类型：</font>" + type;
		holder.mTypeText.setText(Html.fromHtml(type));
		holder.mTypeText.setVisibility(View.GONE);
		holder.mRateText.setText(Html.fromHtml(rate));
		holder.mVolText.setText(Html.fromHtml(vol));
		holder.mDeleteImage.setBackgroundResource(R.drawable.order_delete_nor);
		holder.mDeleteImage.setVisibility(View.VISIBLE);
		holder.mDeleteImage.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					v.setBackgroundResource(R.drawable.order_delete_press);
				} else if(event.getAction() == MotionEvent.ACTION_MOVE){
					v.setBackgroundResource(R.drawable.order_delete_nor);
				} else if(event.getAction() == MotionEvent.ACTION_UP){
					v.setBackgroundResource(R.drawable.order_delete_nor);
					mCancelTask = new CancelOrderTask();
					mCancelTask.setOrderAdapter(mInstance);
					mCancelTask.execute(order);
				}
				return true;
			}
		});
		convertView.setClickable(false);
		convertView.setEnabled(false);
		convertView.setFocusable(false);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groupProjection(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupProjection(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return GROUP_SIZE;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ParentViewHolder holder;
		if(convertView == null){
			holder = new ParentViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.user_orders_title, null);
			holder.mTitleText = (TextView)convertView.findViewById(R.id.user_order_title);
			holder.mArrowView = (ImageView)convertView.findViewById(R.id.user_order_expand);
			convertView.setTag(holder);
		} else {
			holder = (ParentViewHolder)convertView.getTag();
		}
		
		switch(groupPosition){
			case 0:
				holder.mTitleText.setText("BTC/CNY挂单");
				break;
			case 1:
				holder.mTitleText.setText("LTC/CNY挂单");
				break;
			case 2:
				holder.mTitleText.setText("LTC/BTC挂单");
				break;
		}
		if(isExpanded) {
			holder.mArrowView.setBackgroundResource(R.drawable.btn_arrow_up_nor);
		} else {
			holder.mArrowView.setBackgroundResource(R.drawable.btn_arrow_down_nor);
		}
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	static class ChildViewHolder {
		private TextView mTypeText;
		private TextView mRateText;
		private TextView mVolText;
		private ImageView mDeleteImage;
	};
	
	static class ParentViewHolder {
		private TextView mTitleText;
		private ImageView mArrowView;
	};
	
	private ArrayList<UserOrder> groupProjection(int groupPosition){
		switch(groupPosition){
			case 0:
				return mUserBtcCnyOrders;
			case 1:
				return mUserLtcCnyOrders;
			case 2:
				return mUserLtcBtcOrders;
		}
		return new ArrayList<UserOrder>();
	}
	
	public void deleteOrder(UserOrder order){
		String symbol = order.getSymbol();
		if(symbol.equals(UrlConstants.BTC_CNY_TAG)){
			int index = mUserBtcCnyOrders.indexOf(order);
			mUserBtcCnyOrders.remove(index);
		} else if(symbol.equals(UrlConstants.LTC_CNY_TAG)){
			int index = mUserLtcCnyOrders.indexOf(order);
			mUserLtcCnyOrders.remove(index);
		} else if(symbol.equals(UrlConstants.LTC_BTC_TAG)){
			int index = mUserLtcBtcOrders.indexOf(order);
			mUserLtcBtcOrders.remove(index);
		}
		notifyDataSetChanged();
		notifyDataSetInvalidated();
		mCancelTask.cancel(true);
		mListener.onOrderCanceled();
	}
	
	public interface OnOrderCancelListener{
		public void onOrderCanceled();
	}

}
