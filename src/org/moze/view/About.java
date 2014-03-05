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
		String about = "<font color='#ff7722'>关于应用（About）</font><br/>"
				+ "<font color='#22bb00'>开发者（Developer）：</font><font color='#666666'>LordMOS</font><br/>" 
				+ "<font color='#22bb00'>E-mail：</font><font color='#666666'>xanogayu@live.com</font><br/>"
				+ "<font color='#22bb00'>QQ群（Q-Group）：</font><font color='#666666'>306171127</font><br/>" 
				+ "<font color='#22bb00'>反馈（Feedback）：</font><font color='#666666'>若对FxTrader有任何建议和意见，或者反馈Bug，都欢迎给我发邮件，我会尽快处理并更新。</font><br/>"
				+ "<font color='#22bb00'>捐献（Donate）：</font><font color='#666666'>您的支持是我最大的动力！</font>";
		mTitle.setText(Html.fromHtml(about));
	}

}
