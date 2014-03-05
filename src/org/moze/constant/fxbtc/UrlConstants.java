package org.moze.constant.fxbtc;

import java.text.DecimalFormat;
import java.util.HashMap;

/*
 * 1. 数据查询API
 */

public class UrlConstants {
	
	//1-1. 查询当前行情数据
	public static final String QUERY_TICKER_BTC_CNY = "https://data.fxbtc.com/api?op=query_ticker&symbol=btc_cny";
	public static final String QUERY_TICKER_LTC_CNY = "https://data.fxbtc.com/api?op=query_ticker&symbol=ltc_cny";
	public static final String QUERY_TICKER_LTC_BTC = "https://data.fxbtc.com/api?op=query_ticker&symbol=ltc_btc";
	
	//1-2. 查询市场深度数据
	public static final String QUERY_DEPTH_BTC_CNY = "https://data.fxbtc.com/api?op=query_depth&symbol=btc_cny";
	public static final String QUERY_DEPTH_LTC_CNY = "https://data.fxbtc.com/api?op=query_depth&symbol=ltc_cny";
	public static final String QUERY_DEPTH_LTC_BTC = "https://data.fxbtc.com/api?op=query_depth&symbol=ltc_btc";
	
	//1-3. 查询最后n笔交易记录
	//例：若为100笔则需要在该字符串后加100
	//URL url = new URL(QUERY_LAST_TRADES_BTC_CNY + 100);
	public static final String QUERY_LAST_TRADES_BTC_CNY = "https://data.fxbtc.com/api?op=query_last_trades&symbol=btc_cny&count=";
	public static final String QUERY_LAST_TRADES_LTC_CNY = "https://data.fxbtc.com/api?op=query_last_trades&symbol=ltc_cny&count=";
	public static final String QUERY_LAST_TRADES_LTC_BTC = "https://data.fxbtc.com/api?op=query_last_trades&symbol=ltc_btc&count=";
	
	//1-4. 查询历史交易记录
	//注：参数since为返回值中tid，如果since=0，那么从第一条交易记录开始获取，每次返回100条。
	public static final String QUERY_HISTORY_TRADES_BTC_CNY = "https://data.fxbtc.com/api?op=query_history_trades&symbol=btc_cny&since=0";
	public static final String QUERY_HISTORY_TRADES_LTC_CNY = "https://data.fxbtc.com/api?op=query_history_trades&symbol=ltc_cny&since=0";
	public static final String QUERY_HISTORY_TRADES_LTC_BTC = "https://data.fxbtc.com/api?op=query_history_trades&symbol=ltc_btc&since=0";
	
	//跟踪器TAG
	public static final String SYMBOL = "symbol";
	
	public static final String BTC_CNY_TAG = "btc_cny";
	public static final String LTC_CNY_TAG = "ltc_cny";
	public static final String LTC_BTC_TAG = "ltc_btc";

	public static final String BTC = "btc";
	public static final String LTC = "ltc";
	public static final String CNY = "cny";
	
	public static final DecimalFormat FUND_FORMAT = new DecimalFormat("#0.0000");  
	
	public static final int TICKER_MESSAGE = 0x00;
	public static final int DEPTH_MESSAGE = 0x01;
	public static final int LTC_BTC_MESSAGE = 0x02;

	public static final String ASK = "ask";
	public static final String BID = "bid";
	
	public static final int DELAY_FRESH_TIME = 5000;

	public static HashMap<String, String[]> URL_PROJECTIONS = new HashMap<String, String[]>();
	
	static {
		// Add 3 sample items.
		addItem();
	}

	private static void addItem() {
		URL_PROJECTIONS.put(BTC_CNY_TAG, new String[]{
				QUERY_TICKER_BTC_CNY,
				QUERY_DEPTH_BTC_CNY,
				QUERY_LAST_TRADES_BTC_CNY,
				QUERY_HISTORY_TRADES_BTC_CNY
		});

		URL_PROJECTIONS.put(LTC_CNY_TAG, new String[]{
				QUERY_TICKER_LTC_CNY,
				QUERY_DEPTH_LTC_CNY,
				QUERY_LAST_TRADES_LTC_CNY,
				QUERY_HISTORY_TRADES_LTC_CNY
		});
		
		URL_PROJECTIONS.put(LTC_BTC_TAG, new String[]{
				QUERY_TICKER_LTC_BTC,
				QUERY_DEPTH_LTC_BTC,
				QUERY_LAST_TRADES_LTC_BTC,
				QUERY_HISTORY_TRADES_LTC_BTC
		});
	}
	
}
