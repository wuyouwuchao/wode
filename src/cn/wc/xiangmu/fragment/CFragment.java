package cn.wc.xiangmu.fragment;

import java.util.List;



import cn.wc.xiangmu.R;
import cn.wc.xiangmu.adapter.CartItemAdapter;
import cn.wc.xiangmu.app.MyApplication;
import cn.wc.xiangmu.presenter.ICartPresenter;
import cn.wc.xiangmu.presenter.impl.CartPresenter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class CFragment extends Fragment {
	private View view;
	private ListView listView;
	private List<cn.wc.xiangmu.entity.CartItem> items;
	private Button btnEdit;
	private CartItemAdapter adapter;
	private ICartPresenter presenter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.more_c_f, null);
		
		presenter=new CartPresenter();
		listView=(ListView) view.findViewById(R.id.lvCart);
		
		items=MyApplication.getApp().getCart().getItems();
		btnEdit=(Button) view.findViewById(R.id.btnEdit);
		
		setListener();
		return view;
	}
	
	private void setListener() {
		btnEdit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				adapter.deleteToggle();
				if(btnEdit.getText().equals("±‡º≠")){
					btnEdit.setText("»°œ˚");
				}else{
					btnEdit.setText("±‡º≠");
				}
			}
		});
	}

	public void onResume() {
		super.onResume();
		//…Ë÷√  ≈‰∆˜
		setAdapter();
		
	}
	private void setAdapter() {
		adapter=new CartItemAdapter(getActivity(), items, listView);
		adapter.setPresenter(presenter);
		listView.setAdapter(adapter);
	}


}
