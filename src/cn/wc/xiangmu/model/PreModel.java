package cn.wc.xiangmu.model;

import java.util.List;

import cn.wc.xiangmu.entity.PreMoviea;
import cn.wc.xiangmu.entity.PreMovieb;


public interface PreModel extends IModel{
	public List<PreMoviea> showPreMovieaList(AsyncCallback callback);
	public List<PreMovieb> showPreMoviebList(AsyncCallback callback,String tab);
}
