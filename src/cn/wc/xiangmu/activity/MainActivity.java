package cn.wc.xiangmu.activity;

import java.util.ArrayList;


import cn.wc.xiangmu.R;
import cn.wc.xiangmu.fragment.HotFragment;
import cn.wc.xiangmu.fragment.MoreFragment;
import cn.wc.xiangmu.fragment.PreFragment;
import cn.wc.xiangmu.fragment.TopFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private RadioGroup group;
	private ViewPager pager;
	private RadioButton radio0;
	private RadioButton radio1;
	private RadioButton radio2;
	private RadioButton radio3;
	
	
	private ArrayList<Fragment> fragments;
	private MainPagerAdapter pagerAdapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		setViews();
		setPagerAdapter();
		setListeners();
	}

	private void setListeners() {
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					radio0.setChecked(true);
					radio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
					radio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					radio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					radio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					
					break;
				case 1:
					radio1.setChecked(true);
					radio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
					radio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					radio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					radio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					break;
				case 2:
					radio2.setChecked(true);
					radio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
					radio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					radio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					radio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					break;
				case 3:
					radio3.setChecked(true);
					radio3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
					radio0.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					radio1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					radio2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					break;
				}
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		pager.setOffscreenPageLimit(3);
		
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio0:
					pager.setCurrentItem(0);
					break;
				case R.id.radio1:
					pager.setCurrentItem(1);
					break;
				case R.id.radio2:
					pager.setCurrentItem(2);
					break;
				case R.id.radio3:
					pager.setCurrentItem(3);
					break;
				}
			}
		});
	}

	
	private void setPagerAdapter() {
		fragments = new ArrayList<Fragment>();
		fragments.add(new TopFragment());
		fragments.add(new PreFragment());
		fragments.add(new HotFragment());
		fragments.add(new MoreFragment());
		pagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(pagerAdapter);
	}
	private class MainPagerAdapter extends FragmentPagerAdapter{

		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		public Fragment getItem(int position) {
			return fragments.get(position);
		}
		public int getCount() {
			return fragments.size();
		}
	}
	private void setViews() {
		group=(RadioGroup) findViewById(R.id.radioGroup1);
		pager=(ViewPager) findViewById(R.id.ViewPager);
		radio0=(RadioButton) findViewById(R.id.radio0);
		radio1=(RadioButton) findViewById(R.id.radio1);
		radio2=(RadioButton) findViewById(R.id.radio2);
		radio3=(RadioButton) findViewById(R.id.radio3);
	}
	
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
