package cn.wc.xiangmu.presenter;

import cn.wc.xiangmu.entity.PreMovieb;


public interface ICartPresenter extends IPresenter{

	public boolean add(PreMovieb preMovieb);

	public void delete(String movieid);

}
