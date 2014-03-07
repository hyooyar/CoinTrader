package org.moze.view.content;

import org.moze.view.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 	市场行情概览内容碎片，提供各个市场的基本信息
 * 	@version 1.0
 * 	@since 2014年3月6日
 */
public class MarketContentFragment extends Fragment {
	
	private ImageView mMenuBtn;
	
	private LinearLayout mMarketOverviewContainer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.market_content_overview_container, container , false);
		mMenuBtn = (ImageView)mainView.findViewById(R.id.title_menu_btn);
		mMarketOverviewContainer = (LinearLayout)mainView.findViewById(R.id.market_overview_container);
		return mainView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

}
