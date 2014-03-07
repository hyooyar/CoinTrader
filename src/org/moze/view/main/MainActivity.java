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
 *	��ҳ��
 *	@version 1.0
 *	@since 2014��3��5��
 */
public class MainActivity extends Activity implements OnMenuItemSelectListener,
	OnTouchListener{
	
	/**
	 *	�໬�����˵�����
	 */
	private FrameLayout mSlidingMenuContainer;
	/**
	 *	�໬�����˵���Ƭ������չʾ�໬����������
	 */
	private SlidingMenuFragment mSlidingMenu;
	
	/**
	 *	���������������������飬���ã����ڵȾ�����Ϣ��
	 *	��䷽ʽͨ������໬�����˵���
	 */
	private FrameLayout mContentContainer;
	
	/**
	 * 	�г�������Ϣ��Ƭ
	 */
	private MarketContentFragment mMarketContent;
	
	/**
	 * 	��Ƭ����������������Ƭ��������Ƭ��������
	 */
	private FragmentTransaction mFragmentTransaction;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		initView();
		
	}

	
	/**
	 *	��ʼ�����档��ʼ����Ϊ�໬�����˵�����������ܡ�
	 *	����������������������ݵ�Fragment�� 
	 *	@version 1.0
	 *	@since 2014��3��5��
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
		 * 	Ϊ�˹�ܵ���ϲ�Fragment��͸���²�
		 */
		if(v == mContentContainer){
			return true;
		}
		return false;
	}

}
