package cn.wc.xiangmu.fragment;

import java.util.List;


import org.xutils.x;
import org.xutils.view.annotation.ViewInject;


import cn.wc.xiangmu.R;
import cn.wc.xiangmu.adapter.SSlidePageAdapter;
import cn.wc.xiangmu.adapter.TopListAdapter;
import cn.wc.xiangmu.entity.Movie;
import cn.wc.xiangmu.presenter.TopPresenter;
import cn.wc.xiangmu.presenter.impl.TopPresenterImpl;
import cn.wc.xiangmu.view.TopView;
import cn.wc.xiangmu.widget.AutoSlideViewPager;
import cn.wc.xiangmu.widget.AutoSlideViewPager.OnPageSlideSelected;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;

public class TopFragment extends Fragment implements TopView {
	
	
	private TopPresenter presenter;
	
	private List<Movie> movies;
	
	@ViewInject(R.id.movie_lv)
	private ListView listView;
	
	
	@ViewInject(R.id.load_rl)
	private RelativeLayout layout;
	
	@ViewInject(R.id.scl)
	private ScrollView scrollView;
	
	@ViewInject(R.id.view_pager)
	AutoSlideViewPager viewPager;
	
	
	private View view;
	
	private TopListAdapter adapter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view=inflater.inflate(R.layout.fragment_movie, null);
		
		x.view().inject(this,view);
		
		layout=(RelativeLayout) view.findViewById(R.id.load_rl);
		viewPager = (AutoSlideViewPager) view.findViewById(R.id.view_pager);
		
		scrollView.smoothScrollTo(0, 0);
		
		listView.setEmptyView(layout);
		listView.setFocusable(false);
		
		presenter=new TopPresenterImpl(this);
		
		presenter.loadAllTopList(); 
		
		
		
		addlisteners();
		
		return view;
	}
	
	


	private void addlisteners() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				
			AlertDialog.Builder builder=new Builder(getActivity());	
			builder.setNegativeButton("加入收藏夹", new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
			builder.setPositiveButton("查看详情", new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					String url="http://www.cbooo.cn/m/"+movies.get(position).getId();
					
					Uri uri=Uri.parse(url);
					Intent intent=new Intent(Intent.ACTION_VIEW,uri);
					startActivity(intent);
				}
			});
			
			builder.create().show();
				
			}
		});
		
		
	}
	
	public void showList(List<Movie> lists) {
		this.movies=lists;
		
		presenter.loadTitle();
		
		adapter=new TopListAdapter(movies, getActivity());
		
		listView.setAdapter(adapter);
		LayoutParams params=(LayoutParams) getListViewParams();
		listView.setLayoutParams(params);
		
	}
	
	public void showTitle(final List<Bitmap> imgbms) {
		
		SSlidePageAdapter sadapter=new SSlidePageAdapter(getActivity(), imgbms);
		
		viewPager.setAdapter(sadapter);
		viewPager.setAutoSlideDuration(4000);
		viewPager.setOnPageSlideSelected(new OnPageSlideSelected() {
			public void onSlideSelected(final int position) {
			}
			public void onClickSelected(int position) {
				
			}
		});
	}
	
	public ViewGroup.LayoutParams getListViewParams(){
		 int totalHeight = 0;   
	        //便利ListView所有的item，累加所有item的高度就是ListView的实际高度  
	        for (int i = 0; i < adapter.getCount(); i++) {   
	            View listItem = adapter.getView(i, null, listView);   
	            listItem.measure(0, 0);   
	            totalHeight += listItem.getMeasuredHeight();   
	        }   
	        //将累加获取的totalHeight赋值给LayoutParams的height属性  
	        ViewGroup.LayoutParams params = listView.getLayoutParams();   
	        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));   
	        return params;  
		
	}
}
