package cn.wc.xiangmu.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.util.Log;
import cn.wc.xiangmu.entity.PreMoviea;
import cn.wc.xiangmu.entity.PreMovieb;
import cn.wc.xiangmu.model.PreModel;
import cn.wc.xiangmu.util.GlobalConsts;

public class PreModelImpl implements PreModel{
	private List<PreMoviea> preMovieas;
	private List<PreMovieb> preMoviebs;
	
	
	public List<PreMoviea> showPreMovieaList(final AsyncCallback callback) {
		AsyncTask<String, String, List<PreMoviea>> task=new AsyncTask<String, String, List<PreMoviea>>() {
			protected List<PreMoviea> doInBackground(String... params) {
				String url=GlobalConsts.URL_LOAD_PRE_MOVIE_LIST;
				preMovieas=new ArrayList<PreMoviea>();
				
				try {
					Document doc=Jsoup.connect(url).get();
					Element div=doc.getElementsByClass("guideregion").get(0);
					Elements dcv=div.getElementsByTag("dd");
					
					for (int i = 0; i < dcv.size(); i++) {
						Element a=dcv.get(i);
						
						String title=a.getElementsByTag("img").get(0).attr("alt");
						String imgsrc=a.getElementsByTag("img").get(0).attr("src");
						String time=a.getElementsByTag("p").get(0).text();
					PreMoviea moviea=new PreMoviea(title, imgsrc, time);
					
					preMovieas.add(moviea);
					}
				} catch (Exception e) {
				}
				return preMovieas;
			}
			
			protected void onPostExecute(List<PreMoviea> preMovieas) {
				callback.onSuccess(preMovieas);
			}
		};
		task.execute();
		
		
		return null;
	}

	public List<PreMovieb> showPreMoviebList(final AsyncCallback callback,final String tab) {
		AsyncTask<String, String, List<PreMovieb>> task=new AsyncTask<String, String, List<PreMovieb>>() {
			protected List<PreMovieb> doInBackground(String... params) {
				Document doc;
				preMoviebs=new ArrayList<PreMovieb>();
				try {
					doc = Jsoup
							.connect("http://movie.mtime.com/comingsoon/#comingsoon")
							.get();
					Elements div=doc.getElementsByClass(tab);
					for (int i = 0; i < div.size(); i++) {
						Elements a=div.get(i).getElementsByTag("div");
						
						String id=div.get(i).getElementsByTag("li").attr("data-id");
//						Element b=div.get(i).getElementsByClass("movievideo fr").get(0);
//						
//						String z=b.getElementsByTag("a").get(0).attr("href");
////						http://video.mtime.com/62820/?mid=215177
//						String z1=z.substring(23, z.lastIndexOf("/"));
//						String z2="http://static1.mtime.cn/static/flash/outplayer.swf?vid="+z1;
//						
//						Log.v("info","z1-------"+z1);
						String title=a.get(0).getElementsByTag("a").get(0).attr("title");
						//图片路径
						String imgsrc=a.get(0).getElementsByTag("img").get(0).attr("src");
						
						Elements p=a.get(0).getElementsByTag("p");
						
						//上映时间
						String time =p.get(0).text();
						
						//类型
						String movietype=p.get(1).text()+": ";
						Elements type=p.get(1).getElementsByTag("a");
						for (int j = 0; j < type.size(); j++) {
							String s=type.get(j).text();
							movietype=movietype+s+" ";
						}
						
						//国家地区
						String moviecountry=p.get(2).text()+": ";
						Elements country=p.get(2).getElementsByTag("a");
						for (int j = 0; j < country.size(); j++) {
							String s=country.get(j).text();
							moviecountry=moviecountry+s+" ";
						}
						
						//导演
						String moviedirector =p.get(3).text()+": ";
						Elements director=p.get(3).getElementsByTag("a");
						for (int j = 0; j < director.size(); j++) {
							String s=director.get(j).text();
							moviedirector=moviedirector+s+" ";
						}
						
						//主演
						String moviestarring  =p.get(4).text()+": ";
						Elements starring =p.get(4).getElementsByTag("a");
						for (int j = 0; j < starring .size(); j++) {
							String s=starring.get(j).text();
							moviestarring=moviestarring+s+" ";
						}
//						http://static1.mtime.cn/static/flash/outplayer.swf?vid=62820
						
						
						Element b=div.get(i).getElementsByTag("div").last();
						String z=b.getElementsByTag("a").get(0).attr("href");
						
					
						if(z.length()<30){
							z=null;
							}
						if(i==0){
							PreMovieb movieb=new PreMovieb(title, id, imgsrc, time, movietype, moviecountry, moviedirector, moviestarring, z);
							preMoviebs.add(movieb);
						}else
						if(!(title.equals(preMoviebs.get(preMoviebs.size()-1).getTitle()))){
							PreMovieb movieb=new PreMovieb(title, id, imgsrc, time, movietype, moviecountry, moviedirector, moviestarring, z);
							preMoviebs.add(movieb);
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				return preMoviebs;
			}
			@Override
			protected void onPostExecute(List<PreMovieb> result) {
				callback.onSuccess(result);
				
				
			}
		};
		task.execute();
		
		return null;
	}

}
