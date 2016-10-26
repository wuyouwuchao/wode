package cn.wc.xiangmu.adapter;

import java.util.List;

import org.xutils.x;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

import com.bumptech.glide.Glide;

import cn.wc.xiangmu.R;
import cn.wc.xiangmu.entity.PreMoviea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PreMovieaAdapter extends BaseAdapter{
	private Context context;
	private List<PreMoviea>preMovieas;
	private LayoutInflater inflater;
	
	
	public PreMovieaAdapter(Context context, List<PreMoviea> preMovieas) {
		super();
		this.context = context;
		this.preMovieas = preMovieas;
		inflater=LayoutInflater.from(context);
		
	}

	public int getCount() {
		return preMovieas.size();
	}

	@Override
	public PreMoviea getItem(int position) {
		return preMovieas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.pre_image, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.vp_pre_image);
			holder.textView1=(TextView) convertView.findViewById(R.id.vp_pre_title);
			holder.textView2=(TextView) convertView.findViewById(R.id.vp_pre_time);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		
		PreMoviea preMoviea=preMovieas.get(position);
		
		Glide.with(context).load(preMoviea.getImgsrc()).into(holder.imageView);
//		x.image().bind(holder.imageView, preMoviea.getImgsrc());
		
		holder.textView1.setText(preMoviea.getTitle());
		holder.textView2.setText(preMoviea.getTime());
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView imageView;
		TextView textView1;
		TextView textView2;
	}

}
