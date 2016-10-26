package cn.wc.xiangmu.model;

import cn.wc.xiangmu.entity.PreMovieb;


public interface ICartModel extends IModel{

	public boolean add(PreMovieb preMovieb);

	public void delete(String movieid);

}
