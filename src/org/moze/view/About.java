package org.moze.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class About extends Activity {
	
	private TextView mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		mTitle = (TextView) findViewById(R.id.about_title);
		String about = "<font color='#ff7722'>����Ӧ�ã�About��</font><br/>"
				+ "<font color='#22bb00'>�����ߣ�Developer����</font><font color='#666666'>LordMOS</font><br/>" 
				+ "<font color='#22bb00'>E-mail��</font><font color='#666666'>xanogayu@live.com</font><br/>"
				+ "<font color='#22bb00'>QQȺ��Q-Group����</font><font color='#666666'>306171127</font><br/>" 
				+ "<font color='#22bb00'>������Feedback����</font><font color='#666666'>����FxTrader���κν������������߷���Bug������ӭ���ҷ��ʼ����һᾡ�촦�����¡�</font><br/>"
				+ "<font color='#22bb00'>���ף�Donate����</font><font color='#666666'>����֧���������Ķ�����</font>";
		mTitle.setText(Html.fromHtml(about));
	}

}
