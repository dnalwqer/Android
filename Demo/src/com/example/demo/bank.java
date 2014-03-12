package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class bank extends Activity implements OnItemSelectedListener{

	private Spinner spinner;
	private TextView textview;
	private String []bank;
	private String []bankinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank);
		
		
		bank=getResources().getStringArray(R.array.bank);
		bankinfo=getResources().getStringArray(R.array.bankinfo);
		spinner=(Spinner) findViewById(R.id.spinner);
		textview=(TextView)findViewById(R.id.bank_res);
		ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,bank);
		spinner.setAdapter(aa);
		spinner.setOnItemSelectedListener(this);
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		textview.setText(bankinfo[arg2]);
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		textview.setText("");
	}
	
}
