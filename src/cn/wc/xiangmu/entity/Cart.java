package cn.wc.xiangmu.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;


import cn.wc.xiangmu.app.MyApplication;
import cn.wc.xiangmu.util.GlobalConsts;





public class Cart implements Serializable {
	private List<CartItem> items = new ArrayList<CartItem>();
	
	public List<CartItem> getItems() {
		return this.items;
	}

	
	public void deleteMovie(String id) {
		for (CartItem item : items) {
			if (item.getPreMovieb().getId()==id) {
				items.remove(item);
				return;
			}
		}
		saveCart();
	}
	
	
	public boolean add(PreMovieb preMovieb) {
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			if (item.getPreMovieb().equals(preMovieb)) {
				return false;
			}
		}
		//没有添加过
		CartItem item = new CartItem(preMovieb);
		items.add(item);
		saveCart();
		return true;
	}

	/**
	 * 持久化到文件中 下次打开应用购物车依然存在
	 */
	public void saveCart() {
		try {
			File file = new File(MyApplication.getApp().getCacheDir(), GlobalConsts.CART_CACHE_FILE_NAME);
			Log.v("info", "try1---savecart---"+items.size());
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			Log.v("info", "try2---savecart---"+items.size());
			oos.writeObject(Cart.this);
			Log.v("info", "try3---savecart---"+items.size());
			oos.flush();
			oos.close();
		} catch (IOException e) {
			Log.v("info", "catch---savecart---"+items.size());
			e.printStackTrace();
		}
	}


	/**
	 * 从序列化的文件中读取购物车信息
	 *
	 * @return
	 */
	public Cart readCart() {
		try {
			File file = new File(MyApplication.getApp().getCacheDir(), GlobalConsts.CART_CACHE_FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			Cart cart = (Cart) ois.readObject();
			if(cart == null){
				Log.v("info", "cart=null--------");
				return new Cart();
			}
			return cart;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Cart();
	}
}
