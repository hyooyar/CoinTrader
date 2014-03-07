package org.moze.view.slidingmenu;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 *	�໬�����˵���Ƭ��
 *	�����л�������������е�������Ƭ��
 *	@version 1.0
 *	@since 2014��3��5��
 */
public class SlidingMenuFragment extends ListFragment {
	
	private OnMenuItemSelectListener mItemSelectListener;
	
	/**
	 * 	ע��һ�����ⲿ�ص���Listener�������ⲿ�ڵ���໬�����˵�ʱ�����滻������Ƭ
	 * 	@param listener �ⲿ��Ҫʹ�ô�Fragmentʱ����Ҫʵ��һ��OnMenuItemSelectListener
	 */
	public void setOnMenuItemSelectListener(OnMenuItemSelectListener listener){
		mItemSelectListener = listener;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mItemSelectListener.onItemSelect(position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new SlidingMenuAdapter(getActivity()));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getListView().setCacheColorHint(Color.TRANSPARENT);
	}
	
	/**
	 * 	�໬�����˵�ѡ�������
	 * 	�����ڵ���˵���ʱ�л�������Ƭ
	 * 	@version 1.0
	 * 	@since 2014��3��6��
	 */
	public interface OnMenuItemSelectListener {
		/**
		 * 	�ڵ���˵���ʱ��Ҫ�������������
		 * 	@param position ����
		 */
		public void onItemSelect(int position);
	}
}
