package cn.wc.xiangmu.model;

import java.util.List;

import cn.wc.xiangmu.entity.Review;

public interface ReviewModel extends IModel{
	public List<Review> showReviewList(AsyncCallback callback,int p);
}
