package cn.wc.xiangmu.presenter.impl;

import java.util.List;

import cn.wc.xiangmu.entity.Review;
import cn.wc.xiangmu.model.IModel.AsyncCallback;
import cn.wc.xiangmu.model.ReviewModel;
import cn.wc.xiangmu.model.impl.ReviewModelImpl;
import cn.wc.xiangmu.presenter.ReviewPresenter;
import cn.wc.xiangmu.view.ReView;

public class ReviewPresenterImpl implements ReviewPresenter{
	private ReView view;
	private ReviewModel model;
	public ReviewPresenterImpl(ReView view) {
		super();
		this.view = view;
		model=new ReviewModelImpl();
	}

	@Override
	public void showReviewList(int p) {
		model.showReviewList(new AsyncCallback() {
			public void onSuccess(Object success) {
				@SuppressWarnings("unchecked")
				List<Review> reviews=(List<Review>) success;
				view.showList(reviews);
			}
			public void onError(Object error) {
			}
		},p);
	}

	


}
