package cn.wc.xiangmu.adapter;

import java.util.List;

import com.bumptech.glide.Glide;

import cn.wc.xiangmu.R;
import cn.wc.xiangmu.entity.PreMovieb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PreMoviebAdapter extends BaseAdapter {
	private Context context;
	private List<PreMovieb> moviebs;
	private LayoutInflater inflater;
	public PreMoviebAdapter(Context context, List<PreMovieb> moviebs) {
		super();
		this.context = context;
		this.moviebs = moviebs;
		inflater=LayoutInflater.from(context);
	}
	public int getCount() {
		return moviebs.size();
	}
	public PreMovieb getItem(int position) {
		// TODO Auto-generated method stub
		return moviebs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.pre_movieinfo, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.lv_pre_img);
			holder.textView1=(TextView) convertView.findViewById(R.id.textView1);
			holder.textView2=(TextView) convertView.findViewById(R.id.textView2);
			holder.textView3=(TextView) convertView.findViewById(R.id.textView3);
			holder.textView4=(TextView) convertView.findViewById(R.id.textView4);
			holder.textView5=(TextView) convertView.findViewById(R.id.textView5);
			holder.textView6=(TextView) convertView.findViewById(R.id.textView6);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		PreMovieb movieb=moviebs.get(position);
		Glide.with(context).load(movieb.getImgsrc()).into(holder.imageView);
		
		holder.textView1.setText(movieb.getTitle());
		holder.textView2.setText(movieb.getTime());
		holder.textView3.setText(movieb.getMovietype());
		holder.textView4.setText(movieb.getMoviecountry());
		holder.textView5.setText(movieb.getMoviedirector());
		holder.textView6.setText(movieb.getMoviestarring());
		
		return convertView;
	}

	class ViewHolder{
		ImageView imageView;
		TextView textView1;
		TextView textView2;
		TextView textView3;
		TextView textView4;
		TextView textView5;
		TextView textView6;
		
	}
}
