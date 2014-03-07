package org.moze.view.main;

import org.moze.view.R;
import org.moze.view.content.MarketContentFragment;
import org.moze.view.slidingmenu.SlidingMenuFragment;
import org.moze.view.slidingmenu.SlidingMenuFragment.OnMenuItemSelectListener;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;


/**
 *	主页面
 *	@version 1.0
 *	@since 2014年3月5日
 */
public class MainActivity extends Activity implements OnMenuItemSelectListener,
	OnTouchListener{
	
	/**
	 *	侧滑边栏菜单容器
	 */
	private FrameLayout mSlidingMenuContainer;
	/**
	 *	侧滑边栏菜单碎片，真正展示侧滑边栏的区域
	 */
	private SlidingMenuFragment mSlidingMenu;
	
	/**
	 *	内容填充容器，填充如行情，设置，关于等具体信息。
	 *	填充方式通过点击侧滑边栏菜单。
	 */
	private FrameLayout mContentContainer;
	
	/**
	 * 	市场基本信息碎片
	 */
	private MarketContentFragment mMarketContent;
	
	/**
	 * 	碎片填充器，将侧边栏碎片和详情碎片填充进界面
	 */
	private FragmentTransaction mFragmentTransaction;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		initView();
		
	}

	
	/**
	 *	初始化界面。初始界面为侧滑边栏菜单和内容填充框架。
	 *	内容填充框架用于填充各种数据的Fragment。 
	 *	@version 1.0
	 *	@since 2014年3月5日
	 */
	private void initView() {
		mSlidingMenuContainer = (FrameLayout)findViewById(R.id.main_menu);
		mSlidingMenu = new SlidingMenuFragment();
		mSlidingMenu.setOnMenuItemSelectListener(this);
		mFragmentTransaction = getFragmentManager().beginTransaction();
		mFragmentTransaction.replace(R.id.main_menu, mSlidingMenu);
		mContentContainer = (FrameLayout)findViewById(R.id.main_content);
		
		mContentContainer.setOnTouchListener(this);
		mMarketContent = (MarketContentFragment) getFragmentManager().findFragmentById(R.id.main_content);
		if(mMarketContent == null){
			mMarketContent = new MarketContentFragment();
			mFragmentTransaction.replace(R.id.main_content, mMarketContent).commit();
		}
	}


	@Override
	public void onItemSelect(int position) {
		
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		/**
		 * 	为了规避点击上层Fragment穿透到下层
		 */
		if(v == mContentContainer){
			return true;
		}
		return false;
	}

}
