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
	// �������ǵڼ�����ť
	int clickButtonIndex;
	Button[] btnArray = new Button[2];
	// ��ǰ��ʾ���ǵڼ���fragment
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

		// ��ʼ����
		FragmentTransaction fragmentTransaction = getChildFragmentManager()
				.beginTransaction();
		// ����1:add
		fragmentTransaction.add(R.id.more_rl, cFragment);
		// ����2:show
		fragmentTransaction.show(cFragment);
		// �ύ����
		fragmentTransaction.commit();
		
		btnArray[currentShowFragmentIndex].setSelected(true);
	}

	class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			try {
				// �жϵ��������Ǹ���ť
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
				// �жϵ������ǲ��ǵ�ǰ��ť
				if (clickButtonIndex != currentShowFragmentIndex) {
					// ��ʼ����
					FragmentTransaction transaction = getChildFragmentManager()
							.beginTransaction();
					// action1:����������ʾ��fragment
					Fragment hideFragment = fragmentArray[currentShowFragmentIndex];
					transaction.hide(hideFragment);
					// action2:���Ҫ��ʾ��fragment
					Fragment showFragment = fragmentArray[clickButtonIndex];
					if (!showFragment.isAdded()) {
						transaction.add(R.id.more_rl, showFragment);
					}
					// action3:��ʾ
					transaction.show(showFragment);
					// �ύ����
					transaction.commit();
					btnArray[currentShowFragmentIndex].setSelected(false);
					btnArray[clickButtonIndex].setSelected(true);
					currentShowFragmentIndex=clickButtonIndex;
				}
			} catch (Exception e) {
			}
		}
	}}





