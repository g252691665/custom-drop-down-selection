package com.henugao.pullselect;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText et_content;
	private ImageView iv_pull;
	private ListView listView;
	private int popWindowHeigth = 500;
	private List<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUi();
		initData();
	}

	private void initData() {
		for (int i = 0; i < 20; i++) {
			list.add("100000"+i);
		}
		listView = new ListView(MainActivity.this);
		MyAdapter adapter = new MyAdapter();
		listView.setAdapter(adapter);
	}

	private void initUi() {
		et_content = (EditText) findViewById(R.id.et_content);
		iv_pull = (ImageView) findViewById(R.id.iv_pull);
		iv_pull.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showPopWindow();
			}
		});
		
		
	}
	
	private void showPopWindow() {
		PopupWindow popupWindow = new PopupWindow(listView,et_content.getWidth(), popWindowHeigth);
		popupWindow.showAsDropDown(et_content, 0, 0);
	}
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(MainActivity.this, R.layout.adapter_list, null);
			TextView tvUser = (TextView) view.findViewById(R.id.tv_user);
			ImageView ivDel = (ImageView) view.findViewById(R.id.iv_del);
			tvUser.setText(list.get(position));
			
			return view;
		}
		
	}


}
