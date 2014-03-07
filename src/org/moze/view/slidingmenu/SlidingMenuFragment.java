package org.moze.view.slidingmenu;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 *	侧滑边栏菜单碎片。
 *	用于切换内容填充容器中的内容碎片。
 *	@version 1.0
 *	@since 2014年3月5日
 */
public class SlidingMenuFragment extends ListFragment {
	
	private OnMenuItemSelectListener mItemSelectListener;
	
	/**
	 * 	注册一个供外部回调的Listener，用于外部在点击侧滑边栏菜单时进行替换内容碎片
	 * 	@param listener 外部需要使用此Fragment时就需要实现一个OnMenuItemSelectListener
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
	 * 	侧滑边栏菜单选择监听器
	 * 	用于在点击菜单项时切换内容碎片
	 * 	@version 1.0
	 * 	@since 2014年3月6日
	 */
	public interface OnMenuItemSelectListener {
		/**
		 * 	在点击菜单项时需要传出点击的索引
		 * 	@param position 索引
		 */
		public void onItemSelect(int position);
	}
}
