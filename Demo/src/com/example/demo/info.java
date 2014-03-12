package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class info extends Activity implements OnClickListener{

	private Button account,bind,card,weather;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		account=(Button)findViewById(R.id.account);
		bind=(Button) findViewById(R.id.bind);
		card=(Button) findViewById(R.id.card);
		weather=(Button) findViewById(R.id.weather);
		
		bind.setOnClickListener(this);
		weather.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		if(v.getId()==R.id.bind){
			intent.setClass(this, bank.class);
			startActivity(intent);
		}
		else if(v.getId()==R.id.weather){
			intent.setClass(this, account.class);
			startActivity(intent);
		}
	}
}
