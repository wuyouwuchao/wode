package cn.wc.xiangmu.activity;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import cn.wc.xiangmu.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VidioPlayActivity extends Activity{
	private VideoView videoView;
	private String realurl ;
	private ImageView imageView;
	private String title;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vidioplay_activity);
		Intent intent=getIntent();
		String url=intent.getStringExtra("url");
		title=intent.getStringExtra("title");
		realurl=RealUrl(url);
		
		videoView=(VideoView) findViewById(R.id.videoview);
		imageView=(ImageView) findViewById(R.id.fengxiang);
		
		setListener();
	}
	private void setListener() {
		imageView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
		        intent.setType("text/plain");
		        intent.putExtra(Intent.EXTRA_TEXT,title+"   "+realurl);
		        startActivity(Intent.createChooser(intent, "∑÷œÌµΩ"));

			}
		});
	}
	private String RealUrl(final String url) {
		 
		AsyncTask<String, String, String> task=new AsyncTask<String, String, String>() {
			
			@Override
			protected String doInBackground(String... params) {
				Document m;
				String a6=null;
				try {
					m = Jsoup.connect(url).get();
				
//				System.out.println(m);
				Element a4=m.getElementsByTag("head").get(0);
//				System.out.println(a4);
				
				Element a5=a4.getElementsByTag("meta").last();
				System.out.println(a5);
				String url3=a5.attr("content");
				String url4=url3.substring(url3.lastIndexOf("=")+1, url3.length());
				System.out.println(url4);
				
				Document p=Jsoup.connect(url4).get();
				
				a6=p.getElementsByTag("video").get(0).getElementsByTag("source").attr("src");
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return a6;
			}
			@Override
			protected void onPostExecute(String result) {
				Log.v("info", "onpast----url---"+result);
				setUrl(result);
			}
		};
		task.execute();
		return url;
	}
	protected void setUrl(String result) {
		this.realurl=result;
		final Uri uri=Uri.parse(realurl);
		videoView.setMediaController(new MediaController(this));
		videoView.setVideoURI(uri);
		
		new Thread(){
			public void run() {
				videoView.start();
			};
		}.start();
		
	}
}
