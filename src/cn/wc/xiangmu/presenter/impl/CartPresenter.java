package cn.wc.xiangmu.presenter.impl;


import cn.wc.xiangmu.entity.PreMovieb;
import cn.wc.xiangmu.model.ICartModel;
import cn.wc.xiangmu.model.impl.CartModel;
import cn.wc.xiangmu.presenter.ICartPresenter;


public class CartPresenter implements ICartPresenter {

	private ICartModel model;
	
	public CartPresenter() {
		this.model=new CartModel();
	}
		
	

	public boolean add(PreMovieb preMovieb) {
		return model.add(preMovieb);
	}

	public void delete(String movieid) {
		model.delete(movieid);
	}
	
}
