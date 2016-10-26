package cn.wc.xiangmu.fragment;

import cn.wc.xiangmu.R;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class MoreFragment extends Fragment {
	CFragment cFragment;
	ZFragment mFragment;
	// 单击的是第几个按钮
	int clickButtonIndex;
	Button[] btnArray = new Button[2];
	// 当前显示的是第几个fragment
	int currentShowFragmentIndex = 0;
	Fragment[] fragmentArray = new Fragment[2];

	private RadioButton buttona;
	private RadioButton buttonb;
	
	private View view;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_more, null);
		
		cFragment=new CFragment();
		mFragment=new ZFragment();
		fragmentArray[0] = cFragment;
		fragmentArray[1] = mFragment;
		
		buttona=(RadioButton) view.findViewById(R.id.radioa);
		buttonb=(RadioButton) view.findViewById(R.id.radiob);
		
		btnArray[0] = buttona;
		btnArray[1] = buttonb;
		
		MyListener myListener = new MyListener();
		for (Button btn : btnArray) {
			btn.setOnClickListener(myListener);
		}
		showFirstFragment();
		
		return view;
	}
	private void showFirstFragment() {

		// 开始事务
		FragmentTransaction fragmentTransaction = getChildFragmentManager()
				.beginTransaction();
		// 动作1:add
		fragmentTransaction.add(R.id.more_rl, cFragment);
		// 动作2:show
		fragmentTransaction.show(cFragment);
		// 提交事务
		fragmentTransaction.commit();
		
		btnArray[currentShowFragmentIndex].setSelected(true);
	}

	class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			try {
				// 判断单击的是那个按钮
				switch (v.getId()) {
				case R.id.radioa:
					clickButtonIndex = 0;
					buttona.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
					buttonb.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					
					break;
				case R.id.radiob:
					clickButtonIndex = 1;
					buttonb.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
					buttona.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					break;
				}
				// 判断单击的是不是当前按钮
				if (clickButtonIndex != currentShowFragmentIndex) {
					// 开始事务
					FragmentTransaction transaction = getChildFragmentManager()
							.beginTransaction();
					// action1:隐藏正在显示的fragment
					Fragment hideFragment = fragmentArray[currentShowFragmentIndex];
					transaction.hide(hideFragment);
					// action2:添加要显示的fragment
					Fragment showFragment = fragmentArray[clickButtonIndex];
					if (!showFragment.isAdded()) {
						transaction.add(R.id.more_rl, showFragment);
					}
					// action3:显示
					transaction.show(showFragment);
					// 提交事务
					transaction.commit();
					btnArray[currentShowFragmentIndex].setSelected(false);
					btnArray[clickButtonIndex].setSelected(true);
					currentShowFragmentIndex=clickButtonIndex;
				}
			} catch (Exception e) {
			}
		}
	}}





