package cn.wc.xiangmu.view;

import java.util.List;

import android.graphics.Bitmap;

import cn.wc.xiangmu.entity.Movie;

public interface TopView {
	public void showList(List<Movie> lists);
	public void showTitle(List<Bitmap> imgbms);
}
