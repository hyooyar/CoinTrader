package org.moze.view;

import java.util.Timer;

import org.json.JSONException;
import org.json.JSONObject;
import org.moze.background.DepthTask;
import org.moze.background.LoginTask;
import org.moze.background.TickerTask;
import org.moze.background.TradeTask;
import org.moze.background.datatype.DepthOrder;
import org.moze.constant.fxbtc.OperateConstants;
import org.moze.constant.fxbtc.UrlConstants;
import org.moze.view.dummy.DummyContent;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment implements OnTouchListener {

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	private TickerTask mTicker;
	private DepthTask mDepth;
	private Timer mTimer;
	private TextView mTickerText;
	private TextView mAskText;
	private TextView mBidText;

	private Button mAskButton;
	private Button mBidButton;

	private EditText mVolInput;
	private EditText mRateInput;

	private ListView mAskListView;
	private ListView mBidListView;
	private OrderAdapter mAskAdapter;
	private OrderAdapter mBidAdapter;
	private Context mContext;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UrlConstants.TICKER_MESSAGE:
				mTickerText.setText(Html.fromHtml(msg.obj.toString()));
				break;
			case UrlConstants.DEPTH_MESSAGE:
				DepthOrder orders = (DepthOrder) msg.obj;
				mAskAdapter = new OrderAdapter(orders.getAskOrders(), mContext,
						UrlConstants.ASK);
				mBidAdapter = new OrderAdapter(orders.getBidOrders(), mContext,
						UrlConstants.BID);
				mAskListView.setAdapter(mAskAdapter);
				mBidListView.setAdapter(mBidAdapter);
				break;
			}
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);

		if (mItem != null) {
			mTickerText = (TextView) rootView
					.findViewById(R.id.ticker_overview);
			mAskText = (TextView) rootView.findViewById(R.id.title_ask);
			mBidText = (TextView) rootView.findViewById(R.id.title_bid);
			mAskText.setText("卖单");
			mBidText.setText("买单");
			mAskListView = (ListView) rootView.findViewById(R.id.depth_ask);
			mBidListView = (ListView) rootView.findViewById(R.id.depth_bid);
			mTickerText.setText("Loading... Please wait !");

			mAskButton = (Button) rootView.findViewById(R.id.ask_btn);
			mAskButton.setClickable(true);
			mAskButton.setEnabled(true);
			mAskButton.setOnTouchListener(this);
			mBidButton = (Button) rootView.findViewById(R.id.bid_btn);
			mBidButton.setClickable(true);
			mBidButton.setEnabled(true);
			mBidButton.setOnTouchListener(this);

			mVolInput = (EditText) rootView.findViewById(R.id.vol_input_panel);
			mRateInput = (EditText) rootView.findViewById(R.id.rate_input_panel);
		}
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		mTicker = new TickerTask(
				UrlConstants.URL_PROJECTIONS.get(mItem.getId())[UrlConstants.TICKER_MESSAGE],
				mHandler);
		mDepth = new DepthTask(
				UrlConstants.URL_PROJECTIONS.get(mItem.getId())[UrlConstants.DEPTH_MESSAGE],
				mHandler);
		mTimer = new Timer();
		mTimer.schedule(mTicker, UrlConstants.DELAY_FRESH_TIME,
				UrlConstants.DELAY_FRESH_TIME);
		mTimer.schedule(mDepth, UrlConstants.DELAY_FRESH_TIME,
				UrlConstants.DELAY_FRESH_TIME);
	}

	@Override
	public void onPause() {
		super.onPause();
		mTicker.cancel();
		mDepth.cancel();
		mTimer.cancel();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		try {
			if (v == mBidButton) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_bid_press);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_bid_normal);
					String vol = mVolInput.getText().toString();
					String rate = mRateInput.getText().toString();
					if (!vol.equals("") && !rate.equals("")) {
						JSONObject tradeOrder = new JSONObject();
						tradeOrder.put(OperateConstants.VOL, vol);
						tradeOrder.put(OperateConstants.RATE, rate);
						tradeOrder.put(UrlConstants.SYMBOL, mItem.getId());
						tradeOrder.put(OperateConstants.TYPE, "buy");
						TradeTask trade = new TradeTask(mContext);
						trade.execute(tradeOrder);
					} else {
						Toast.makeText(mContext, "请填写完整挂单信息", 250).show();
					}
				}
				return true;
			} else if (v == mAskButton) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_ask_press);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_ask_normal);
					String vol = mVolInput.getText().toString();
					String rate = mRateInput.getText().toString();
					if (!vol.equals("") && !rate.equals("")) {
						JSONObject tradeOrder = new JSONObject();
						tradeOrder.put(OperateConstants.VOL, vol);
						tradeOrder.put(OperateConstants.RATE, rate);
						tradeOrder.put(UrlConstants.SYMBOL, mItem.getId());
						tradeOrder.put(OperateConstants.TYPE, "sell");
						TradeTask trade = new TradeTask(mContext);
						trade.execute(tradeOrder);
					} else {
						Toast.makeText(mContext, "请填写完整挂单信息", 250).show();
					}
				}
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

}
