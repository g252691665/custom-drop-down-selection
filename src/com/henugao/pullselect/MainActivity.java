package com.henugao.pullselect;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	private int popWindowHeigth = 300;
	private List<String> list = new ArrayList<String>();
	private PopupWindow popupWindow;
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
		listView.setVerticalScrollBarEnabled(false); //隐藏垂直方向的listview的滚动条
		MyAdapter adapter = new MyAdapter();
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("MainActivity", "posi:"+position);
				et_content.setText(list.get(position));
				
			}
		});
		
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
		if (popupWindow == null) {
			popupWindow = new PopupWindow(listView,et_content.getWidth(), popWindowHeigth);
		}

		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		//popupWindow.setOutsideTouchable(true);
		//popupWindow.setTouchable(true);
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			final View view = View.inflate(MainActivity.this, R.layout.adapter_list, null);
			TextView tvUser = (TextView) view.findViewById(R.id.tv_user);
			ImageView ivDel = (ImageView) view.findViewById(R.id.iv_del);
			tvUser.setText(list.get(position));
			ivDel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					list.remove(position);
					notifyDataSetChanged();
					
					int listviewHeight = view.getHeight() * list.size();
					popupWindow.update(et_content.getWidth(), listviewHeight > popWindowHeigth ? popWindowHeigth : listviewHeight);
					if (list.size() == 0) {
						popupWindow.dismiss();
						iv_pull.setVisibility(View.INVISIBLE);
					}
				}
			});
			return view;
		}
		
	}


}
