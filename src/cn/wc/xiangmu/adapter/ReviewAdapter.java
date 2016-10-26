package cn.wc.xiangmu.adapter;

import java.util.List;

import com.bumptech.glide.Glide;

import cn.wc.xiangmu.R;
import cn.wc.xiangmu.entity.Review;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ReviewAdapter extends BaseAdapter{
	private List<Review> reviews;
	private Context context;
	private LayoutInflater inflater;
	
	
	public ReviewAdapter(List<Review> reviews, Context context) {
		super();
		this.reviews = reviews;
		this.context = context;
		inflater=LayoutInflater.from(context);
	}
	

	@Override
	public int getCount() {
		return reviews.size();
	}


	@Override
	public Review getItem(int position) {
		return reviews.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.revierw_item, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.reiv);
			holder.textView1=(TextView) convertView.findViewById(R.id.t1);
			holder.textView2=(TextView) convertView.findViewById(R.id.t2);
			holder.textView3=(TextView) convertView.findViewById(R.id.t3);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		Review review=reviews.get(position);
		
		Glide.with(context).load(review.getIamgesrc()).into(holder.imageView);
		holder.textView1.setText(review.getTitle());
		holder.textView2.setText(review.getTime());
		holder.textView3.setText(review.getTxt());
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView imageView;
		TextView textView1;
		TextView textView2;
		TextView textView3;
		
	}

}
