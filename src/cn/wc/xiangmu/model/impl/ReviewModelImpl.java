package cn.wc.xiangmu.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import cn.wc.xiangmu.entity.Review;
import cn.wc.xiangmu.model.ReviewModel;
import cn.wc.xiangmu.util.GlobalConsts;

public class ReviewModelImpl implements ReviewModel{
	private List<Review> reviews;
	
	public List<Review> showReviewList(final AsyncCallback callback,final int p) {
		reviews=new ArrayList<Review>();
		
		AsyncTask<String, String, List<Review>> task=new AsyncTask<String, String, List<Review>>() {
			protected List<Review> doInBackground(String... params) {
				String url=GlobalConsts.URL_LOAD_HOT_REVIEW_LIST+p+".html";
				try {
					Document doc=Jsoup.connect(url).get();
					Elements e=doc.getElementsByClass("reviListBox");
					Elements b=e.get(0).getElementsByTag("dl");
					
					for (int i = 0; i < b.size(); i++) {
						
						Element a=b.get(i);
						String imagesrc="http://www.51oscar.com"+a.getElementsByTag("dt").get(0).getElementsByTag("img").attr("src");
						String link=a.getElementsByTag("dd").get(0).getElementsByClass("t").get(0).getElementsByTag("a").get(0).attr("href");
						String title=a.getElementsByTag("dd").get(0).getElementsByClass("t").get(0).getElementsByTag("a").get(0).attr("title");
						String text=a.getElementsByTag("dd").get(0).getElementsByTag("p").get(1).text();
						String time=a.getElementsByTag("dd").get(0).getElementsByTag("p").get(1).getElementsByTag("time").get(0).text();
						String txt=a.getElementsByTag("dd").get(0).getElementsByClass("txt").get(0).text();
						
						Review review=new Review(imagesrc, title, time, txt, link);
						reviews.add(review);
					}
						
				} catch (IOException e) {
					e.printStackTrace();
				}
				return reviews;
			}
			@Override
			protected void onPostExecute(List<Review> result) {
				callback.onSuccess(result);
			}
		};
		task.execute();
		
		
		
		return null;
	}

}
