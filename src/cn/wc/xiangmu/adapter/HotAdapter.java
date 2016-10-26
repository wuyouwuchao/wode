package cn.wc.xiangmu.adapter;

import java.util.List;

import com.bumptech.glide.Glide;

import cn.wc.xiangmu.R;
import cn.wc.xiangmu.entity.Movieb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HotAdapter extends BaseAdapter{
	private List<Movieb> moviebs;
	private Context context;
	private LayoutInflater inflater;
	
	public HotAdapter(List<Movieb> moviebs, Context context) {
		super();
		this.moviebs = moviebs;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return moviebs.size();
	}

	@Override
	public Movieb getItem(int position) {
		return moviebs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			
			convertView=inflater.inflate(R.layout.play_item, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.paly_iv);
			holder.textView=(TextView) convertView.findViewById(R.id.play_name);
			convertView.setTag(holder);
		}
		Movieb movieb=moviebs.get(position);
		holder=(ViewHolder) convertView.getTag();
		Glide.with(context).load(movieb.getImagesrc()).into(holder.imageView);
		holder.textView.setText(movieb.getName());
		return convertView;
	}
	class ViewHolder{
		ImageView imageView;
		TextView textView;
	}
	
}
