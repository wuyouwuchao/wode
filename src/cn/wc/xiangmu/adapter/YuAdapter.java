package cn.wc.xiangmu.adapter;

import java.util.List;

import com.bumptech.glide.Glide;

import cn.wc.xiangmu.R;
import cn.wc.xiangmu.entity.Movieb;
import cn.wc.xiangmu.entity.Yugaopian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class YuAdapter extends BaseAdapter{
	private List<Yugaopian> moviebs;
	private Context context;
	private LayoutInflater inflater;
	
	public YuAdapter(List<Yugaopian> moviebs, Context context) {
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
	public Yugaopian getItem(int position) {
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
			
			convertView=inflater.inflate(R.layout.play_item2, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.paly_iv);
			holder.textView=(TextView) convertView.findViewById(R.id.play_name);
			holder.textView2=(TextView) convertView.findViewById(R.id.time);
			convertView.setTag(holder);
		}
		Yugaopian movieb=moviebs.get(position);
		holder=(ViewHolder) convertView.getTag();
		Glide.with(context).load(movieb.getIamgeurl()).into(holder.imageView);
		holder.textView.setText(movieb.getTitle()+"1");
		holder.textView2.setText(movieb.getTime());
		return convertView;
	}
	class ViewHolder{
		ImageView imageView;
		TextView textView;
		TextView textView2;
	}
	
}
