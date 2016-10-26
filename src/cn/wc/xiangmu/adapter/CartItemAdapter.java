package cn.wc.xiangmu.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bumptech.glide.Glide;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.wc.xiangmu.R;
import cn.wc.xiangmu.entity.CartItem;
import cn.wc.xiangmu.presenter.ICartPresenter;

public class CartItemAdapter extends BaseAdapter {

	private Context context;
	private List<CartItem> items;
	private LayoutInflater inflater;
	private ListView listView;
	private ICartPresenter presenter;
	
	public CartItemAdapter(Context context, List<CartItem> items, ListView listView) {
		this.context = context;
		this.items = items;
		this.listView = listView;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public CartItem getItem(int i) {
		return items.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHolder holder = null;
		if (view == null) {
			view = inflater.inflate(R.layout.item_cart_lv_cartitem, null);
			holder = new ViewHolder();
			holder.ivMoviePic=(ImageView) view.findViewById(R.id.cart_iv);
			holder.tvmovieName=(TextView) view.findViewById(R.id.tvmovieName);
			holder.tvtime=(TextView) view.findViewById(R.id.tvtime);
			holder.tvtime2=(TextView) view.findViewById(R.id.tvtime2);
			holder.ivDel=(ImageView) view.findViewById(R.id.ivDel);
			view.setTag(holder);
		}
		holder = (ViewHolder) view.getTag();
		CartItem item = getItem(i);
		String picPath = item.getPreMovieb().getImgsrc(); 
		Glide.with(context).load(picPath).into(holder.ivMoviePic);
		holder.tvmovieName.setText(item.getPreMovieb().getTitle());
		holder.tvtime.setText(item.getPreMovieb().getTime());
		holder.ivDel.setTag("ivDel" + i);
		
		String s=item.getPreMovieb().getTime();
		String yue = null;
		String ri = null;
		String nian = null;
		if(s.length()>0){
			nian=s.substring(s.indexOf("2"), s.lastIndexOf("年"));
			yue=s.substring(s.lastIndexOf("年")+1,s.lastIndexOf("月"));
			if(yue.length()==1){
				yue="0"+yue;
			}
			ri=s.substring(s.lastIndexOf("月")+1,s.lastIndexOf("日"));
			if(ri.length()==1){
				ri="0"+ri;
			}
		}
		String time=nian+"-"+yue+"-"+ri;
		
		
		Log.v("info", "time--------"+time.toString()+item.getPreMovieb());
		
		Date data=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time2=format.format(data);
		int a=getTwoDay(time,time2);
		
		getItem(i).getPreMovieb().setT(a);
		
		if(a<0){
			a=-a;
			holder.tvtime2.setText("首映已经过了"+a+"天啦,快去电影院包场吧");
		}else{
		holder.tvtime2.setText("距离首映还有:"+a+"天!");
		}
		
		
		if(!show) {
			holder.ivDel.setScaleX(0);
			holder.ivDel.setScaleY(0);
			holder.ivDel.setVisibility(View.GONE);
		}else{
			holder.ivDel.setScaleX(1);
			holder.ivDel.setScaleY(1);
		}

		holder.ivDel.setOnClickListener(new DelItemListener(i));

		return view;
	}
	
	public  int getTwoDay(String sj1, String sj2) {
		  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		  long day = 0;
		  try {
		   java.util.Date date = myFormatter.parse(sj1);
		   java.util.Date mydate = myFormatter.parse(sj2);
		   day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		  } catch (Exception e) {
		  }
		  return (int) day;
		}

	public boolean show = false;

	public void deleteToggle() {
		int maxPosition = getCount() - 1;
		if (show) { //立即隐藏
			for (int i = 0; i <= maxPosition; i++) {
				final ImageView ivDel = (ImageView) listView.findViewWithTag("ivDel" + i);
				
				ObjectAnimator anim = ObjectAnimator.ofFloat(ivDel, "abc", 1f, 0f);
				anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator valueAnimator) {
						float val = (Float) valueAnimator.getAnimatedValue();
						ivDel.setScaleX(val);
						ivDel.setScaleY(val);
						if(val==0){
							ivDel.setVisibility(View.GONE);
							
						}
					}
				});
				anim.setDuration(500);
				anim.start();
			}
			show = false;
		} else { //立即显示
			for (int i = 0; i <= maxPosition; i++) {
				final ImageView ivDel = (ImageView) listView.findViewWithTag("ivDel" + i);
				ObjectAnimator anim = ObjectAnimator.ofFloat(ivDel, "abc", 0f, 1f);
				anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator valueAnimator) {
						float val = (Float) valueAnimator.getAnimatedValue();
						if(val==0){
							ivDel.setVisibility(View.VISIBLE);
						}
						ivDel.setScaleX(val);
						ivDel.setScaleY(val);
					}
				});
				anim.setDuration(500);
				anim.start();
				
			}
			show = true;
		}
	}

	class ViewHolder {
		ImageView ivMoviePic;
		TextView tvmovieName;
		TextView tvtime;
		TextView tvtime2;
		ImageView ivDel;
	}
	class DelItemListener implements View.OnClickListener{
		private int position;
		public DelItemListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View view) {
			presenter.delete(getItem(position).getPreMovieb().getId());
			
			CartItemAdapter.this.notifyDataSetChanged();
		}
	}
	public void setPresenter(ICartPresenter presenter){
		this.presenter = presenter;
	}
}




