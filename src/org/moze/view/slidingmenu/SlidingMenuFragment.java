package org.moze.view.slidingmenu;

import org.moze.view.About;
import org.moze.view.LoginActivity;
import org.moze.view.MainActivity;
import org.moze.view.SplashActivity;

import android.app.ListFragment;
import android.content.Intent;
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

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		Intent intent;
		switch (position) {
		case 0:
			intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(getActivity(), About.class);
			startActivity(intent);
			break;
		default:
			break;
		}
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
}
