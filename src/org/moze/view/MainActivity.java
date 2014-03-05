package org.moze.view;

import org.moze.view.slidingmenu.SlidingMenuFragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;


/**
 *	主页面
 *	@version 1.0
 *	@since 2014年3月5日
 */
public class MainActivity extends Activity {
	
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
		mFragmentTransaction = getFragmentManager().beginTransaction();
		mFragmentTransaction.replace(R.id.main_menu, mSlidingMenu).commit();
		mContentContainer = (FrameLayout)findViewById(R.id.main_content);
	}
	
}
