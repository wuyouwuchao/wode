package cn.wc.xiangmu.activity;

import java.util.Random;

import cn.wc.xiangmu.R;
import cn.wc.xiangmu.app.MyApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class LogoActivity extends Activity{
	private ImageView imageView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		
		imageView=(ImageView) findViewById(R.id.logo_img);
		int[] imgIds={
				R.drawable.img1,
				R.drawable.img2,
				R.drawable.img3,
				R.drawable.img4,
				R.drawable.img5,
				R.drawable.img6,
				R.drawable.img7
		};
		
		imageView.setImageResource(imgIds[new Random().nextInt(6)]);
		
		Handler handler=new Handler();
		
		handler.postDelayed(new Runnable() {
			
			public void run() {
				Intent intent=new Intent(MyApplication.getApp(),MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				finish();
				startActivity(intent);
				
			}
		}, 5000);
	}
	@Override
	protected void onDestroy() {
		finishActivity(RESULT_FIRST_USER);
		super.onDestroy();
		
	}
	
	
	
}
