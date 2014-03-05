package org.moze.view.dummy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.moze.constant.fxbtc.UrlConstants;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	static {
		// Add 3 sample items.

		addItem(new DummyItem(UrlConstants.BTC_CNY_TAG, "BTC/CNY行情"));
		addItem(new DummyItem(UrlConstants.LTC_CNY_TAG, "LTC/CNY行情"));
		addItem(new DummyItem(UrlConstants.LTC_BTC_TAG, "LTC/BTC行情"));
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.mId, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		private String mId;
		private String mLabel;

		public DummyItem(String id, String content) {
			mId = id;
			mLabel = content;
		}

		public String toString() {
			return mLabel;
		}
		
		public String getId() {
			return mId;
		}
		
		
	}
}
