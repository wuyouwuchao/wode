package cn.wc.xiangmu.presenter.impl;

import java.util.List;

import android.graphics.Bitmap;

import cn.wc.xiangmu.entity.Movie;
import cn.wc.xiangmu.model.IModel.AsyncCallback;
import cn.wc.xiangmu.model.TopModel;
import cn.wc.xiangmu.model.impl.TopModelImpl;
import cn.wc.xiangmu.presenter.TopPresenter;
import cn.wc.xiangmu.view.TopView;


public class TopPresenterImpl implements TopPresenter{
	private TopView view;
	private TopModel model;
	
	
	public TopPresenterImpl(TopView view) {
		super();
		this.view = view;
		model=new TopModelImpl();
	}
	public void loadAllTopList() {
		model.loadAllTopList(new AsyncCallback() {
			public void onSuccess(Object success) {
				@SuppressWarnings("unchecked")
				List<Movie> lists=(List<Movie>) success;
				view.showList(lists);
			}
			public void onError(Object error) {
			}
		});
	}
	
	public void loadTitle() {
		model.loadtitle(new AsyncCallback() {
			public void onSuccess(Object success) {
				List<Bitmap> imgbms=(List<Bitmap>) success;
				view.showTitle(imgbms);
			}
			public void onError(Object error) {
				
			}
		});
	}

}
