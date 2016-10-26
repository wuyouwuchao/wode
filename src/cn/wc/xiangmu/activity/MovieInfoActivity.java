package cn.wc.xiangmu.activity;

import java.util.ArrayList;
import java.util.List;


import cn.wc.xiangmu.R;
import cn.wc.xiangmu.adapter.PreMoviebAdapter;
import cn.wc.xiangmu.app.MyApplication;
import cn.wc.xiangmu.entity.PreMovieb;
import cn.wc.xiangmu.presenter.impl.CartPresenter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MovieInfoActivity extends Activity {
	
	private PreMoviebAdapter adapter;
	private ListView listview;
	private EditText etSearch;
	private List<PreMovieb> pmbs=new ArrayList<PreMovieb>();
	private RelativeLayout empty;
	private List<PreMovieb> pmb=new ArrayList<PreMovieb>();
	private ImageButton imageButton;
	private CartPresenter presenter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_list);
		
		pmbs=MyApplication.getApp().getMoviebas();
		
		empty=(RelativeLayout) findViewById(R.id.load_rl);
		listview=(ListView) findViewById(R.id.movie_info_list);
		
		listview.setEmptyView(empty);
		Log.v("info", "pmbs---------"+pmbs.size());
		imageButton=(ImageButton) findViewById(R.id.imageButton);
		etSearch=(EditText) findViewById(R.id.etSearch);
		presenter=new CartPresenter();
		
		adapter=new PreMoviebAdapter(this, pmbs);
		listview.setAdapter(adapter);
		
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			String a=etSearch.getText().toString().trim();
			if(a!=null){
				for (int i = 0; i < pmbs.size(); i++) {
					if(a.equals(pmbs.get(i).getTitle())){
						pmb.add(pmbs.get(i));
					}
				}
				adapter=new PreMoviebAdapter(getApplicationContext(), pmb);
				listview.setAdapter(adapter);
			}else {
				Toast.makeText(MovieInfoActivity.this, "请输入电影名称", Toast.LENGTH_SHORT).show();
			}	
			}
		});
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog.Builder builder=new Builder(MovieInfoActivity.this);
				builder.setPositiveButton("加入收藏", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						boolean a=presenter.add(pmbs.get(position));
						if(a){
							Toast.makeText(getApplicationContext(), "收藏成功", 0).show();
						}else{
							Toast.makeText(getApplicationContext(), "已经收藏过啦", 0).show();
						}
					}
				});
				builder.setNegativeButton("观看预告片", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				
				
				builder.create().show();
				
				
			}
		});
	}
	
}
