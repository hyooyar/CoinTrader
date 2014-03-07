package org.moze.view.slidingmenu;

/**
 *	侧滑边栏菜单项，包括描述和资源id
 *	@version 1.0
 *	@since 2014年3月5日
 */
public class SlidingMenuItem {
	/**
	 *	菜单栏显示描述
	 */
	private String mTag;
	/**
	 * 	菜单栏显示图片资源id
	 */
	private int mResourceId;
	
	/**
	 * 初始化一个侧滑边栏菜单项
	 * @param tag 菜单栏显示描述，用于显示菜单栏当前项的描述
	 * @param resId 菜单栏显示图片资源，用于显示菜单栏的icon资源
	 */
	public SlidingMenuItem( String tag , int resId ){
		mTag = tag;
		mResourceId = resId;
	}
	
	/**
	 * 	获取当前菜单项的描述
	 *  @return String 描述
	 */
	public String getTag(){
		return mTag;
	}
	
	/**
	 * 	获取当前菜单项的图片资源id
	 *  @return int 图片资源id
	 */
	public int getResourceId(){
		return mResourceId;
	}
	
	/**
	 * 设置侧滑边栏菜单项描述
	 * @param tag 菜单栏显示描述，用于显示菜单栏当前项的描述
	 */
	public void setTag( String tag ){
		mTag = tag;
	}
	
	/**
	 * 设置侧滑边栏菜单项显示图片资源id
	 * @param resId 菜单栏显示图片资源，用于显示菜单栏的icon资源
	 */
	public void setResourceId( int resId ){
		mResourceId = resId;
	}
}
