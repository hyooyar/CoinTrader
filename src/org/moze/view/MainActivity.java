package org.moze.view;

import org.moze.view.slidingmenu.SlidingMenuFragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;


/**
 *	��ҳ��
 *	@version 1.0
 *	@since 2014��3��5��
 */
public class MainActivity extends Activity {
	
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
		mFragmentTransaction = getFragmentManager().beginTransaction();
		mFragmentTransaction.replace(R.id.main_menu, mSlidingMenu).commit();
		mContentContainer = (FrameLayout)findViewById(R.id.main_content);
	}
	
}
