package cn.wc.xiangmu.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;

import cn.wc.xiangmu.entity.Movieb;
import cn.wc.xiangmu.entity.Yugaopian;
import cn.wc.xiangmu.model.YuModel;

public class YuModelImpl implements YuModel{
	private List<Movieb> moviebs;
	@Override
	public List<Movieb> LoadHotlist(final AsyncCallback callback) {
		moviebs=new ArrayList<Movieb>();
		
		AsyncTask< String, String, List<Movieb>> task=new AsyncTask<String, String, List<Movieb>>() {
			
			@Override
			protected List<Movieb> doInBackground(String... params) {
				
			
					
					Document doc;
					try {
						doc = Jsoup.connect("https://movie.douban.com/").get();
					
					Element dvc=doc.getElementsByClass("ui-slide-content").get(0);
					Elements a=dvc.getElementsByTag("li");
					
					for (int i = 0; i < a.size(); i++) {
						if(a.get(i).hasClass("ui-slide-item")){
							
							
							String url=a.get(i).attr("data-trailer");
							
							String url1=a.get(i).getElementsByTag("img").attr("src");
							
							String name=a.get(i).getElementsByTag("img").attr("alt");
							
							Movieb movieb=new Movieb(url1, name, url);
							
							moviebs.add(movieb);
						}
			}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return moviebs;
			};
			@Override
			protected void onPostExecute(List<Movieb> result) {
				callback.onSuccess(result);
			}
		};
		task.execute();
		
		return null;
	}
	
	
	private List<Yugaopian> yugaopians;
	@Override
	public List<Yugaopian> LoadYulist(final String url,final AsyncCallback callback) {
		yugaopians=new ArrayList<Yugaopian>();
		AsyncTask< String, String, List<Yugaopian>> task=new AsyncTask<String, String, List<Yugaopian>>() {
			@Override
			protected List<Yugaopian> doInBackground(String... params) {
				Document d;
				try {
					d = Jsoup.connect(url).get();
				
				Element a1=d.getElementsByClass("article").get(0);
				Element a2=a1.getElementsByTag("ul").get(0);
				Elements a3=a2.getElementsByTag("li");
				for (int i = 0; i < a3.size(); i++) {
					Element b1=a3.get(i);
					String url2=b1.getElementsByTag("a").attr("href");
					String imageurl=b1.getElementsByTag("a").get(0).getElementsByTag("img").attr("src");
					String time=b1.getElementsByTag("a").get(0).getElementsByTag("strong").text();
					String title=b1.getElementsByTag("p").get(0).text();;
					
					 Yugaopian yugaopian=new Yugaopian(imageurl, url2, title, time);
					 yugaopians.add(yugaopian);
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return yugaopians;
			}
			@Override
			protected void onPostExecute(List<Yugaopian> result) {
				callback.onSuccess(result);
			}
		};
		task.execute();
		
		return null;
	}

	
	
	
	

}
