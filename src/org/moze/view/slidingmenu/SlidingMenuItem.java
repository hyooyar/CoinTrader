package org.moze.view.slidingmenu;

/**
 * �໬�����˵��������������Դid
 * @version 1.0 
 * @since 2014��3��5�� 
 */

public class SlidingMenuItem {
	/**
	 *	�໬�����˵�������
	 */
	private String mTag;
	/**
	 * 	�໬�����˵���ͼƬ��Դid
	 */
	private int mResourceId;
	
	/**
	 *  ��ʼ��һ���໬�����˵���	 
	 * 	@param tag �໬�����˵�����������
	 *  @param resId �໬�����˵���ͼƬ��Դid
	 */
	public SlidingMenuItem( String tag , int resId ){
		mTag = tag;
		mResourceId = resId;
	}
	
	/**
	 * 	��ȡ�˵���������Ϣ  
	 *	@return String �˵�������
	 */
	public String getTag(){
		return mTag;
	}
	
	/**
	 * 	��ȡ�˵�������ͼƬ��Դid
	 *  @return int ͼƬ��Դid
	 */
	public int getResourceId(){
		return mResourceId;
	}
	
	/**
	 * 	���ò˵���������Ϣ  
	 *	@param tag �˵���������Ϣ
	 */
	public void setTag( String tag ){
		mTag = tag;
	}
	
	/**
	 * 	���ò˵�������ͼƬ��Դid
	 *  @param resId �˵�������ͼƬ��Դid
	 */
	public void setResourceId( int resId ){
		mResourceId = resId;
	}
}
