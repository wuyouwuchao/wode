package cn.wc.xiangmu.fragment;

import cn.wc.xiangmu.widget.MyListview.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.wc.xiangmu.R;
import cn.wc.xiangmu.adapter.ReviewAdapter;
import cn.wc.xiangmu.entity.Review;
import cn.wc.xiangmu.presenter.ReviewPresenter;
import cn.wc.xiangmu.presenter.impl.ReviewPresenterImpl;
import cn.wc.xiangmu.view.ReView;
import cn.wc.xiangmu.widget.MyListview;


public class ZFragment extends Fragment implements ReView{
	private View view;
	private ReviewPresenter presenter;
	private int p=1;
	private ReviewAdapter adapter;
	private List<Review> reviews=new ArrayList<Review>();
	private MyListview listview;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.more_z_f, null);
		listview=(MyListview) view.findViewById(R.id.review_list);
		
		presenter=new ReviewPresenterImpl(this);
		presenter.showReviewList(p);
		
		MyOnRefreshListener listener=new MyOnRefreshListener();
		listview.setOnRefreshListener(listener);
		
		return view;
	}
	
	
	
	
	public void showList(List<Review> lists) {
		this.reviews.addAll(lists);
		adapter=new ReviewAdapter(reviews, getActivity());
		listview.setAdapter(adapter);
	}
	
	public class MyOnRefreshListener implements OnRefreshListener{
		@Override
		public void onRefresh(MyListview myListview) {
			new Thread(){
				public void run() {
				try {
					this.sleep(2000);
					
					p+=1;
					presenter.showReviewList(p);
					
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							listview.completeRefresh();
							/*
							// 运行在主线程
							adapter.notifyDataSetChanged();
							Log.i("TAGTAG", "count1111: "+(listview.getCount()-1));
							listview.requestFocusFromTouch();//获取焦点 
							listview.setSelection(listview.getCount()-1);
							Log.i("TAGTAG", "count2222: "+(listview.getCount()-1));*/
							
							
							listview.postDelayed(new Runnable() {								
								@Override
								public void run() {
									adapter.notifyDataSetChanged();
									listview.requestFocusFromTouch();//获取焦点 
									listview.setSelection(listview.getCount()-11);
								}
							}, 1000);
						}
					});
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				};
			}.start();
			
		}
	}
}
