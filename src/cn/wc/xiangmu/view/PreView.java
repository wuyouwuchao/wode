package cn.wc.xiangmu.view;

import java.util.List;

import cn.wc.xiangmu.entity.PreMoviea;
import cn.wc.xiangmu.entity.PreMovieb;


public interface PreView {
	void showPreMovieaList(List<PreMoviea> preMovieas);
	void showPreMoviebaList(List<PreMovieb> preMoviebs);
}
