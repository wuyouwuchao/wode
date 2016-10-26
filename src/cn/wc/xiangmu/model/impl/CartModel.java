package cn.wc.xiangmu.model.impl;

import cn.wc.xiangmu.app.MyApplication;
import cn.wc.xiangmu.entity.Cart;
import cn.wc.xiangmu.entity.PreMovieb;
import cn.wc.xiangmu.model.ICartModel;


public class CartModel implements ICartModel {
	private Cart cart;

	public CartModel() {
		cart = MyApplication.getApp().getCart();
	}

	@Override
	public boolean add(PreMovieb preMovieb) {
		return cart.add(preMovieb);
	}

	@Override
	public void delete(String movieid) {
		cart.deleteMovie(movieid);
	}


}
