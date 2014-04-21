package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{

	private Button ok,cancel;
	private EditText username,password;
	private TextView forget;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ok=(Button) findViewById(R.id.ok);
		cancel=(Button) findViewById(R.id.cancel);
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
		
		username=(EditText)findViewById(R.id.username);
		password=(EditText) findViewById(R.id.password);
		
		forget=(TextView) findViewById(R.id.forget);
		forget.setText(Html.fromHtml("<a href='http://www.baidu.com'>忘记密码?</a>"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.ok){
			if(username.getText().toString().equals("dnalwqer")&&password.getText().toString().equals("123456")){
				Intent intent=new Intent();
				intent.setClass(this, info.class);
				startActivity(intent);
			}
			else{
				Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
			}
		}
		else if(v.getId()==R.id.cancel){
			username.setText("");
			password.setText("");
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		username.setText("");
		password.setText("");
	}

	
}
