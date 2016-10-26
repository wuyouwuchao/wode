package cn.wc.xiangmu.fragment;

import java.util.GregorianCalendar;
import java.util.List;

import cn.wc.xiangmu.R;
import cn.wc.xiangmu.activity.PlayListActivity;
import cn.wc.xiangmu.adapter.HotAdapter;
import cn.wc.xiangmu.entity.Movieb;
import cn.wc.xiangmu.presenter.impl.YupresenterImpl;
import cn.wc.xiangmu.view.HotView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class HotFragment extends Fragment implements HotView{
	private View view;
	private YupresenterImpl yupresenterImpl;
	private GridView gridView;
	private HotAdapter adapter;
	private List<Movieb> moviebs;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_palying, null);
		gridView=(GridView) view.findViewById(R.id.play_gv);
		yupresenterImpl=new YupresenterImpl(this, null);
		yupresenterImpl.loadHot();
		
		setListeners();
		
		return view;
	}
	private void setListeners() {
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Movieb movieb=moviebs.get(position);
				
				Intent intent=new Intent(getActivity(),PlayListActivity.class);
				intent.putExtra("url", movieb.getNexturl());
				Log.v("info", "onitmeclick---- url----"+movieb.getNexturl());
				getActivity().startActivity(intent);
				
			}
		});
	}
	public void showHotList(List<Movieb> moviebs) {
		this.moviebs=moviebs;
		adapter=new HotAdapter(moviebs, getActivity());
		gridView.setAdapter(adapter);
	}
	
	
}
