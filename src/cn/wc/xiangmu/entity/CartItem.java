package cn.wc.xiangmu.entity;

import java.io.Serializable;

public class CartItem implements Serializable{
	private PreMovieb PreMovieb;

	public PreMovieb getPreMovieb() {
		return PreMovieb;
	}

	public void setPreMovieb(PreMovieb preMovieb) {
		PreMovieb = preMovieb;
	}

	public CartItem(PreMovieb preMovieb) {
		super();
		PreMovieb = preMovieb;
	}

	

	
	
}
