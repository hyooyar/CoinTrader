package org.moze.view.slidingmenu;

/**
 *	�໬�����˵��������������Դid
 *	@version 1.0
 *	@since 2014��3��5��
 */
public class SlidingMenuItem {
	/**
	 *	�˵�����ʾ����
	 */
	private String mTag;
	/**
	 * 	�˵�����ʾͼƬ��Դid
	 */
	private int mResourceId;
	
	/**
	 * ��ʼ��һ���໬�����˵���
	 * @param tag �˵�����ʾ������������ʾ�˵�����ǰ�������
	 * @param resId �˵�����ʾͼƬ��Դ��������ʾ�˵�����icon��Դ
	 */
	public SlidingMenuItem( String tag , int resId ){
		mTag = tag;
		mResourceId = resId;
	}
	
	/**
	 * 	��ȡ��ǰ�˵��������
	 *  @return String ����
	 */
	public String getTag(){
		return mTag;
	}
	
	/**
	 * 	��ȡ��ǰ�˵����ͼƬ��Դid
	 *  @return int ͼƬ��Դid
	 */
	public int getResourceId(){
		return mResourceId;
	}
	
	/**
	 * ���ò໬�����˵�������
	 * @param tag �˵�����ʾ������������ʾ�˵�����ǰ�������
	 */
	public void setTag( String tag ){
		mTag = tag;
	}
	
	/**
	 * ���ò໬�����˵�����ʾͼƬ��Դid
	 * @param resId �˵�����ʾͼƬ��Դ��������ʾ�˵�����icon��Դ
	 */
	public void setResourceId( int resId ){
		mResourceId = resId;
	}
}
