package org.moze.view;

import java.util.ArrayList;

import org.moze.background.TraderApp;
import org.moze.background.UserInfoTask;
import org.moze.background.UserOrdersTask;
import org.moze.background.UserOrdersTask.onUserOrdersUpdateListener;
import org.moze.background.datatype.UserOrder;
import org.moze.constant.fxbtc.UrlConstants;
import org.moze.view.UserOrdersAdapter.OnOrderCancelListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends Activity implements OnTouchListener , OnOrderCancelListener{
	
	private TextView mUserInfoOverview;
	
	private Button mEnterMarketBtn;
	private Button mEnterAboutBtn;
	private ExpandableListView mUserOrdersListView;
	private UserOrdersAdapter mUserOrdersAdapter;
	
	private UserInfoTask mUserInfoTask;

	private UserOrdersTask mBtcCnyOrdersTask;
	private UserOrdersTask mLtcCnyOrdersTask;
	private UserOrdersTask mLtcBtcOrdersTask;


	private boolean mIsBtcCnyLoaded = false;
	private boolean mIsLtcCnyLoaded = false;
	private boolean mIsLtcBtcLoaded = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info_activity);
		mUserInfoOverview = (TextView) findViewById(R.id.userinfo_overview);
		mUserInfoOverview.setText("欢迎回来," + TraderApp.getUserName() + "!");
		mUserOrdersListView = (ExpandableListView) findViewById(R.id.uerinfo_orders);
		mUserOrdersListView.setGroupIndicator(null);
		mUserOrdersListView.setFocusable(true);
		mUserOrdersListView.setCacheColorHint(Color.TRANSPARENT);
		mUserOrdersListView.setEnabled(true);
		mUserOrdersListView.setClickable(true);
		mUserOrdersListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if(mUserOrdersListView.isGroupExpanded(groupPosition)){
					mUserOrdersListView.collapseGroup(groupPosition);
				} else {
					mUserOrdersListView.expandGroup(groupPosition);
				}
				return true;
			}
		});
		mEnterMarketBtn = (Button) findViewById(R.id.enter_market_btn);
		mEnterMarketBtn.setClickable(true);
		mEnterMarketBtn.setEnabled(true);
		mEnterMarketBtn.setOnTouchListener(this);
		mEnterAboutBtn = (Button) findViewById(R.id.enter_about);
		mEnterAboutBtn.setClickable(true);
		mEnterAboutBtn.setEnabled(true);
		mEnterAboutBtn.setOnTouchListener(this);
		mUserOrdersAdapter = new UserOrdersAdapter(this);
		mUserOrdersAdapter.setOnOrderCancelListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mUserInfoTask = new UserInfoTask();
		mUserInfoTask.setUserInfoTextView(mUserInfoOverview);
		mUserInfoTask.execute(TraderApp.getToken());
		mBtcCnyOrdersTask = new UserOrdersTask();
		mBtcCnyOrdersTask.setOnUserOrdersUpdateLitsener(new onUserOrdersUpdateListener() {
			@Override
			public void onUserOrderUpdate(ArrayList<UserOrder> orders) {
				mUserOrdersAdapter.setBtcCnyOrders(orders);
				mIsBtcCnyLoaded = true;
				initOrderView();
			}
		});
		mBtcCnyOrdersTask.execute(UrlConstants.BTC_CNY_TAG);
		mLtcCnyOrdersTask = new UserOrdersTask();
		mLtcCnyOrdersTask.setOnUserOrdersUpdateLitsener(new onUserOrdersUpdateListener() {
			@Override
			public void onUserOrderUpdate(ArrayList<UserOrder> orders) {
				mUserOrdersAdapter.setLtcCnyOrders(orders);
				mIsLtcCnyLoaded = true;
				initOrderView();
			}
		});
		mLtcCnyOrdersTask.execute(UrlConstants.LTC_CNY_TAG);
		mLtcBtcOrdersTask = new UserOrdersTask();
		mLtcBtcOrdersTask.setOnUserOrdersUpdateLitsener(new onUserOrdersUpdateListener() {
			@Override
			public void onUserOrderUpdate(ArrayList<UserOrder> orders) {
				mUserOrdersAdapter.setLtcBtcOrders(orders);
				mIsLtcBtcLoaded = true;
				initOrderView();
			}
		});
		mLtcBtcOrdersTask.execute(UrlConstants.LTC_BTC_TAG);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mBtcCnyOrdersTask.cancel(true);
		mLtcCnyOrdersTask.cancel(true);
		mLtcBtcOrdersTask.cancel(true);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(v == mEnterMarketBtn){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				v.setBackgroundResource(R.drawable.btn_bid_press);
			} else if(event.getAction() == MotionEvent.ACTION_UP){
				v.setBackgroundResource(R.drawable.btn_bid_normal);
				Intent intent = new Intent(this,ItemListActivity.class);
				startActivity(intent);
			}
			return true;
		} else if(v == mEnterAboutBtn){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				v.setBackgroundResource(R.drawable.btn_ask_press);
			} else if(event.getAction() == MotionEvent.ACTION_UP){
				v.setBackgroundResource(R.drawable.btn_ask_normal);
				Intent intent = new Intent(this,About.class);
				startActivity(intent);
			}
			return true;
		}
		return false;
	}
	
	private void initOrderView() {
		if(mIsBtcCnyLoaded && mIsLtcCnyLoaded && mIsLtcBtcLoaded){
			mUserOrdersListView.setVisibility(View.VISIBLE);
			mUserOrdersListView.setAdapter(mUserOrdersAdapter);
			for(int i = 0; i < mUserOrdersAdapter.getGroupCount() ; i++){
				mUserOrdersListView.expandGroup(i);
			}
		}
	}

	@Override
	public void onOrderCanceled() {
		mUserInfoTask = new UserInfoTask();
		mUserInfoTask.setUserInfoTextView(mUserInfoOverview);
		mUserInfoTask.execute(TraderApp.getToken());
		Toast.makeText(this, "撤单成功！", UrlConstants.DELAY_FRESH_TIME).show();
	}

}
