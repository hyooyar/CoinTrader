package org.moze.view.slidingmenu;

/**
 * 侧滑边栏菜单项，包括描述和资源id
 * @version 1.0 
 * @since 2014年3月5日 
 */

public class SlidingMenuItem {
	/**
	 *	侧滑边栏菜单项描述
	 */
	private String mTag;
	/**
	 * 	侧滑边栏菜单项图片资源id
	 */
	private int mResourceId;
	
	/**
	 *  初始化一个侧滑边栏菜单项	 
	 * 	@param tag 侧滑边栏菜单项文字描述
	 *  @param resId 侧滑边栏菜单项图片资源id
	 */
	public SlidingMenuItem( String tag , int resId ){
		mTag = tag;
		mResourceId = resId;
	}
	
	/**
	 * 	获取菜单项描述信息  
	 *	@return String 菜单项描述
	 */
	public String getTag(){
		return mTag;
	}
	
	/**
	 * 	获取菜单项描述图片资源id
	 *  @return int 图片资源id
	 */
	public int getResourceId(){
		return mResourceId;
	}
	
	/**
	 * 	设置菜单项描述信息  
	 *	@param tag 菜单项描述信息
	 */
	public void setTag( String tag ){
		mTag = tag;
	}
	
	/**
	 * 	设置菜单项描述图片资源id
	 *  @param resId 菜单项描述图片资源id
	 */
	public void setResourceId( int resId ){
		mResourceId = resId;
	}
}
