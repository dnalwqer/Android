package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class account extends Activity implements OnItemSelectedListener{
	
	private Handler handler;
	private GridView gridview;
	private List<cityData> templist;
	private TextView textview;
	private ProgressBar progressbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		progressbar=(ProgressBar)findViewById(R.id.progress);
		progressbar.setVisibility(View.VISIBLE);
		
		textview=(TextView)findViewById(R.id.time);
		templist=new ArrayList<account.cityData>();
		
		handler=new myHandler();
		Thread t=new myThread();
		t.start();
		
		gridview=(GridView)findViewById(R.id.grid);
		gridview.setAdapter(new myAdapter(templist));
		gridview.setOnItemSelectedListener(this);
		
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		progressbar.setVisibility(View.GONE);
	}


	class myHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			templist.clear();
			String xml=(String)msg.obj;
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder db=factory.newDocumentBuilder();
				Document doc=db.parse(new ByteArrayInputStream(xml.getBytes()));
				
				Element root=doc.getDocumentElement();
				
				NodeList cityList=root.getChildNodes();
				
				Node city1=cityList.item(1);
				NamedNodeMap n=city1.getAttributes();
				Node time=n.getNamedItem("time");
				textview.setText(time.getNodeValue());
				for(int i=0;i<cityList.getLength();i++){
					Node citynode=cityList.item(i);
					if(citynode.getNodeName().equalsIgnoreCase("city")){
						cityData data=new cityData(citynode);
						templist.add(data);
					}
				}

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	class cityData{
		public String cityname,stateDetailed,temNow;
		public cityData(Node node){
			NamedNodeMap n=node.getAttributes();
			this.cityname=n.getNamedItem("cityname").getNodeValue();
			this.stateDetailed=n.getNamedItem("stateDetailed").getNodeValue();
			this.temNow=n.getNamedItem("temNow").getNodeValue();
		}
	}
	
	class myThread extends Thread{
		public void run(){
			http httpclient=new http();
			String xml=null;
			String url="http://flash.weather.com.cn/wmaps/xml/beijing.xml";
			while(true){
				try {
					xml=httpclient.getXML(url);
					Message msg=handler.obtainMessage();
					msg.obj=xml;
					handler.sendMessage(msg);
					Thread.sleep(10*1000);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
		}
	}
	

	class myAdapter extends BaseAdapter{
	
		private List<cityData> templist;
		public myAdapter(List<cityData> templist){
			super();
			this.templist=templist;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return templist.size();
		}
	
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			 
			View row=convertView;
			ViewWrapper wrapper=null;
			if(row==null)
			{
				row = account.this.getLayoutInflater().inflate(R.layout.grid, null);  
				wrapper=new ViewWrapper(row);
				row.setTag(wrapper);
			}else
				wrapper = (ViewWrapper) row.getTag();

//	        TextView tv_city = (TextView)row.findViewById(R.id.place);  
//	        TextView tv_temp = (TextView)row.findViewById(R.id.temp);   
	        wrapper.getLabel().setText(templist.get(position).cityname);  
	        wrapper.getIcon().setText(templist.get(position).temNow+"℃");  
	        
	        View v=row.findViewById(R.id.bg);
	        v.setBackgroundColor(Color.YELLOW);
	        return row; 
		}
		
	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(this, templist.get(arg2).cityname+":"+templist.get(arg2).temNow,Toast.LENGTH_SHORT ).show();
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

class http {
	private HttpClient httpclient;
	public http(){
		httpclient=new DefaultHttpClient();
	}
	
	public String getXML(String url) throws ClientProtocolException, IOException{
		String xml=null;
		HttpGet httpget=new HttpGet(url);
		HttpResponse response=httpclient.execute(httpget);
		if(response.getStatusLine().getStatusCode()==200){
			HttpEntity entity=response.getEntity();
			if(entity!=null){
				xml=EntityUtils.toString(entity);
			}
		}
		return xml;
	}
}

class ViewWrapper{
    View base;
    TextView label = null;
    TextView icon = null;
    
    ViewWrapper(View base){
        this.base = base;
    }
    
    TextView getLabel(){
        if(label == null)
            label = (TextView)base.findViewById(R.id.place);
        return label;
    }
    
    TextView getIcon(){
        if(icon == null)
            icon = (TextView)base.findViewById(R.id.temp);
        return icon;
    }
}
