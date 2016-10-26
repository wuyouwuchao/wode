package cn.wc.xiangmu.fragment;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;


import cn.wc.xiangmu.R;
import cn.wc.xiangmu.activity.MovieInfoActivity;
import cn.wc.xiangmu.adapter.PreMovieaAdapter;
import cn.wc.xiangmu.adapter.PreMoviebAdapter;
import cn.wc.xiangmu.app.MyApplication;
import cn.wc.xiangmu.entity.PreMoviea;
import cn.wc.xiangmu.entity.PreMovieb;
import cn.wc.xiangmu.presenter.PrePresenter;
import cn.wc.xiangmu.presenter.impl.CartPresenter;
import cn.wc.xiangmu.presenter.impl.PrePresenterImpl;
import cn.wc.xiangmu.view.PreView;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PreFragment extends Fragment implements PreView {

	private GridView gridView;

	private PreMovieaAdapter adapter;
	private PrePresenter prePresenterImpl;


	private ListView listView;
	private TextView textView;
	
	private Spinner spinner;
	private RelativeLayout layout;
	private List<PreMovieb>pmbas;
	private List<PreMovieb>pmbbs;
	private List<PreMovieb>pmbcs;
	private List<PreMovieb>pmb=new ArrayList<PreMovieb>();
	private PreMoviebAdapter adapter2;
	private CartPresenter presenter;
	
	private int tab=1;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_premovie, null);
		x.view().inject(view);

		gridView = (GridView) view.findViewById(R.id.gridview);
		
		listView = (ListView) view.findViewById(R.id.pre_listview);
		View footer=inflater.inflate(R.layout.footer_pre, null);
		listView.addFooterView(footer);
		
		layout=(RelativeLayout) view.findViewById(R.id.load_rl);
		
		prePresenterImpl = new PrePresenterImpl(this);
		
		prePresenterImpl.showPreMovieaList();
		
		prePresenterImpl.showPreMoviebList("tab1");
		
		presenter=new CartPresenter();
		
		spinner=(Spinner) view.findViewById(R.id.spinner);
		
		adapter2=new PreMoviebAdapter(getActivity(), pmb);
		
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog.Builder builder=new Builder(getActivity());
				builder.setPositiveButton("加入收藏夹", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						PreMovieb movieb=pmbas.get(position);
						boolean a=presenter.add(movieb);
						
						if(a){
							Toast.makeText(getActivity(), "收藏成功", 0).show();
						}else{
							Toast.makeText(getActivity(), "已经收藏过啦", 0).show();
						}
					}
				});
				builder.setNegativeButton("观看预告片", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					    String flashurl=pmbas.get(position).getMoviefilsh();
					    if(flashurl!=null){
					    	Uri uri=Uri.parse(flashurl);
					    Log.v("info", "uri   2-------"+uri.toString());
					    	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					    	startActivity(intent);
					    }
					    else{
					    	Toast.makeText(getActivity(), "暂无资源啊 亲~", Toast.LENGTH_SHORT).show();
					    }
					}
				});
				
				
				builder.create().show();
				
				
			}
		});
		
		
		
		textView=(TextView) footer.findViewById(R.id.a_tv);
		
		textView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent intent=new Intent(getActivity(),MovieInfoActivity.class);
				intent.putExtra("tab", tab);
				startActivity(intent);
			}
		});
		
		
		return view;
	}
	
	
	

	public void showPreMovieaList(List<PreMoviea> preMovieas) {

		int size = preMovieas.size();

		int length = 100;

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int gridviewWidth = (int) (size * (length + 4) * density);
		int itemWidth = (int) (length * density);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
		gridView.setLayoutParams(params); // 重点
		gridView.setColumnWidth(itemWidth); // 重点
		gridView.setHorizontalSpacing(10); // 间距
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(size); // 重点

		adapter = new PreMovieaAdapter(getActivity(), preMovieas);
		gridView.setAdapter(adapter);

	}

	@Override
	public void showPreMoviebaList(List<PreMovieb> preMoviebs) {
		this.pmbas=preMoviebs;
		MyApplication.getApp().setMoviebas(preMoviebs);
		listView.setEmptyView(layout);
		if(pmbas.size()>15){
		this.pmb=pmbas.subList(0, 15);
		adapter2 = new PreMoviebAdapter(getActivity(),
				pmb);
		listView.setAdapter(adapter2);}
	}
	
}
