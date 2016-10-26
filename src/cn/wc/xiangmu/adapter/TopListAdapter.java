package cn.wc.xiangmu.adapter;

import java.util.List;

import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.wc.xiangmu.R;
import cn.wc.xiangmu.entity.Movie;

public class TopListAdapter extends BaseAdapter{
	private List<Movie> movies;
	private Context context;
	private LayoutInflater inflater;
	
	public TopListAdapter(List<Movie> movies, Context context) {
		super();
		this.movies = movies;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	public int getCount() {
		return movies.size();
	}

	public Movie getItem(int position) {
		return movies.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.list_itme_movie, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.iv_movie);
			holder.tvname=(TextView) convertView.findViewById(R.id.textView1);
			holder.tvreal=(TextView) convertView.findViewById(R.id.textView2);
			holder.tvtotal=(TextView) convertView.findViewById(R.id.textView3);
			holder.tvtime=(TextView) convertView.findViewById(R.id.textView4);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		Movie moive=movies.get(position);
		
//		x.image().bind(holder.imageView, moive.getPicurl());
		
		holder.tvname.setText("ӰƬ ��"+moive.getTitle());
		holder.tvreal.setText("ʵʱƱ�� ��"+moive.getRealtime()+"��");
		holder.tvtotal.setText("��Ʊ�� :"+moive.getTotal()+"��");
		String uri="sdcard/myfloder"+moive.getId()+".jpg";
		Glide.with(context).load(uri).into(holder.imageView);
		holder.tvtime.setText("����ӳ���� "+moive.getDatas());
		
		
		
		return convertView;
	}
	private class ViewHolder{
		ImageView imageView;
		TextView tvname;
		TextView tvreal;
		TextView tvtotal;
		TextView tvtime;
		ImageView ivtop;
	}

}
