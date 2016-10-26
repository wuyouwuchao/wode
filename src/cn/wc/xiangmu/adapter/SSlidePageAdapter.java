package cn.wc.xiangmu.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SSlidePageAdapter extends PagerAdapter {
	private Context context;
	private List<View> viewList = new ArrayList<View>();

	public SSlidePageAdapter(Context context,List<Bitmap> imgbms) {
		this.context = context;
		this.viewList = setViewList(imgbms);
	}

	private List<View> setViewList(List<Bitmap> imgbms) {
		List<View> viewList = new ArrayList<View>();
		ImageView firstView = new ImageView(context);
		
		Log.v("info", "imgbms"+imgbms.size());
		
		firstView.setImageBitmap(imgbms.get(imgbms.size() - 1));
		viewList.add(firstView);
		for (Bitmap i : imgbms) {
			ImageView iv = new ImageView(context);
			iv.setImageBitmap(i);
			viewList.add(iv);
		}
		ImageView lastView = new ImageView(context);
		lastView.setImageBitmap(imgbms.get(0));
		viewList.add(lastView);
		return viewList;
	}

	@Override
	public void finishUpdate(View arg0) {

	}

	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		return viewList.size();
	}

	@Override
	public Object instantiateItem(View view, int position) {
		((ViewGroup) view).addView(viewList.get(position));
		return viewList.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {

	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		try {
			((ViewPager) arg0).removeView(viewList.get(arg1));
		} catch (Exception e) {
		}
	}

}
