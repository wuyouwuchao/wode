package cn.wc.xiangmu.presenter.impl;

import java.util.List;


import cn.wc.xiangmu.entity.PreMoviea;
import cn.wc.xiangmu.entity.PreMovieb;
import cn.wc.xiangmu.model.IModel.AsyncCallback;
import cn.wc.xiangmu.model.PreModel;
import cn.wc.xiangmu.model.impl.PreModelImpl;
import cn.wc.xiangmu.presenter.PrePresenter;
import cn.wc.xiangmu.view.PreView;

public class PrePresenterImpl implements PrePresenter{
	private PreView view;
	private PreModel model; 
	
	
	public PrePresenterImpl(PreView view) {
		super();
		this.view = view;
		model=new PreModelImpl();
	}
	public void showPreMovieaList() {
		model.showPreMovieaList(new AsyncCallback() {
			@SuppressWarnings("unchecked")
			public void onSuccess(Object success) {
				List<PreMoviea> preMovieas=(List<PreMoviea>)success;
				view.showPreMovieaList(preMovieas);
			}
			
			public void onError(Object error) {
				
			}
		});
		
	}
	@Override
	public void showPreMoviebList(final String tab) {
		model.showPreMoviebList(new AsyncCallback() {
			public void onSuccess(Object success) {
				@SuppressWarnings("unchecked")
				List<PreMovieb> preMoviebs=(List<PreMovieb>)success;
				
				if(tab.equals("tab1")){
					view.showPreMoviebaList(preMoviebs);
				}
			}
			public void onError(Object error) {
			}
		},tab);
		
	}

}
