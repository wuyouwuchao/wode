package cn.wc.xiangmu.activity;

import java.util.List;

import cn.wc.xiangmu.R;
import cn.wc.xiangmu.adapter.YuAdapter;
import cn.wc.xiangmu.entity.Yugaopian;
import cn.wc.xiangmu.presenter.impl.YupresenterImpl;
import cn.wc.xiangmu.view.YuView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PlayListActivity extends Activity implements YuView{
	private YupresenterImpl yupresenterImpl;
	private ListView listView;
	private YuAdapter adapter;
	private List<Yugaopian> yugaopians;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_list);
		Intent intent=getIntent();
		String url=intent.getStringExtra("url");
		
		Log.v("info", "playactivity------geturl---"+url);
		listView=(ListView) findViewById(R.id.paly_list_gv);
		
		yupresenterImpl=new YupresenterImpl(null, this);
		yupresenterImpl.loadYu(url);
		
		setListener();
		
	}

	private void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Yugaopian yugaopian=yugaopians.get(position);
				
				Intent intent=new Intent(getApplicationContext(),VidioPlayActivity.class);
				intent.putExtra("url", yugaopian.getUrl());
				intent.putExtra("title", yugaopian.getTitle());
				startActivity(intent);
			}
		});
	}

	@Override
	public void showYulist(List<Yugaopian> yugaopians) {
		this.yugaopians=yugaopians;
		adapter=new YuAdapter(yugaopians, getApplication());
		listView.setAdapter(adapter);
	}
}
