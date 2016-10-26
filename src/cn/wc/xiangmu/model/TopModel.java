package cn.wc.xiangmu.model;

import java.util.List;

import android.graphics.Bitmap;

import cn.wc.xiangmu.entity.Movie;

public interface TopModel extends IModel{
	
	public List<Movie> loadAllTopList(AsyncCallback callback);
	
	
	public List<Bitmap> loadtitle(AsyncCallback callback);
	
}
