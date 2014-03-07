package org.moze.view.slidingmenu;

import java.util.ArrayList;

import org.moze.constant.SlidingMenuConstants;
import org.moze.view.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 	�໬�����˵�ʹ�õ�������������
 * 	@version 1.0
 * 	@since 2014��3��5��
 */
public class SlidingMenuAdapter extends BaseAdapter {
	
	/**
	 * 	���ڱ���˵���ʾ�ĸ����˵���
	 */
	private ArrayList<SlidingMenuItem> mMenuList = new ArrayList<SlidingMenuItem>();
	
	private Context mContext;
	
	/**
	 * 	��ʼ��������ʱ��Ԥ�������װ���
	 */
	public SlidingMenuAdapter(Context context){
		mContext = context;
		mMenuList.add(new SlidingMenuItem(SlidingMenuConstants.MARKET, -1));
//		mMenuList.add(new SlidingMenuItem(SlidingMenuConstants.SETTING, -1));
		mMenuList.add(new SlidingMenuItem(SlidingMenuConstants.ABOUT, -1));
	}

	@Override
	public int getCount() {
		return mMenuList.size();
	}

	@Override
	public Object getItem(int position) {
		return mMenuList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler hodler;
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.sliding_menu_item, null);
			hodler = new ViewHodler();
			hodler.mIcon = (ImageView)convertView.findViewById(R.id.item_icon);
			hodler.mTag = (TextView)convertView.findViewById(R.id.item_tag);
			convertView.setTag(hodler);
		} else {
			hodler = (ViewHodler)convertView.getTag();
		}
		if(mMenuList.get(position).getResourceId() == -1){
			hodler.mIcon.setVisibility(View.GONE);
		} else {
			hodler.mIcon.setImageResource(mMenuList.get(position).getResourceId());
		}
		hodler.mTag.setText(mMenuList.get(position).getTag());
		return convertView;
	}
	
	public static class ViewHodler{
		ImageView mIcon;
		TextView mTag;
	};

}
