package org.moze.view;

import org.moze.background.fxbtc.LoginTask;
import org.moze.background.fxbtc.TraderApp;
import org.moze.background.fxbtc.LoginTask.onLoginStateChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnTouchListener, onLoginStateChangeListener{
	private EditText mUsernameText;
	private EditText mPasswordText;
	private Button mLogin;
	private LoginTask mLoginTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		mUsernameText = (EditText)findViewById(R.id.login_username_pannel);
		mPasswordText = (EditText)findViewById(R.id.login_password_pannel);
		mLogin = (Button)findViewById(R.id.login_btn);
		mLogin.setClickable(true);
		mLogin.setEnabled(true);
		mLogin.setOnTouchListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(mLoginTask != null){
			mLoginTask.cancel(true);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(v == mLogin){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				v.setBackgroundResource(R.drawable.btn_bid_press);
			} else if(event.getAction() == MotionEvent.ACTION_UP){
				v.setBackgroundResource(R.drawable.btn_bid_normal);
				String username = mUsernameText.getText().toString();
				String password = mPasswordText.getText().toString();
				if(!username.equals("") && !password.equals("")) {
					mLoginTask = new LoginTask();
					mLoginTask.setOnLoginStateChangeListener(this);
					mLoginTask.execute(username,password);
				} else {
					Toast.makeText(this, "请填写用户信息", 250).show();
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void onLoginStart() {
		mLogin.setText("正在登录...");
		mLogin.setEnabled(false);
	}

	@Override
	public void onLoginSuccess(String token) {
		Toast.makeText(this, "登录成功！", 250).show();
		TraderApp.setToken(token);
		Intent intent = new Intent(this,UserInfoActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onLoginError(int errorCode, String msg) {
		mLogin.setText("登录");
		mLogin.setEnabled(true);
		if (errorCode == -200) {
			Toast.makeText(this, "用户名或密码错误！", 250).show();
		} else {
			Toast.makeText(this, "登录失败！错误码为" + errorCode + ",错误信息为" + msg, 250).show();
		}
		
	}
}
