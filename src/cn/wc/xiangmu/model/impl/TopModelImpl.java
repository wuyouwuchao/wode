package cn.wc.xiangmu.model.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import cn.wc.xiangmu.entity.Movie;
import cn.wc.xiangmu.model.TopModel;
import cn.wc.xiangmu.util.GlobalConsts;


public class TopModelImpl implements TopModel{
	private List<Movie> movies;
	private List<Bitmap> imgbms;
	public List<Movie> loadAllTopList(final AsyncCallback callback) {
		AsyncTask<String, String, List<Movie>> task=new AsyncTask<String, String, List<Movie>>() {
			private ArrayList<String> imgurl;

 
			protected List<Movie> doInBackground(String... params) {
				
				String url=GlobalConsts.URL_LOAD_HOT_AUDIO_LIST;
				List<Movie> movies=new ArrayList<Movie>();
				try {
					Document doc=Jsoup.connect(url).get();
					Element div = doc.getElementsByClass("cinametable").get(0);
					Elements dov=div.children();
					
					for (int i = 0; i < dov.size(); i++) {
						Element a=dov.get(i);
						String id=a.getElementsByTag("tr").get(0).attr("id");
						
						Elements b=a.getElementsByTag("td");
						String top=b.get(0).text();
						String title=b.get(1).text();
						String realtime=b.get(2).text();
						String boxofficeof=b.get(3).text();
						String rowseatof=b.get(5).text();
						String total=b.get(4).text();
						String datas=b.get(6).text();
						Movie movie=new Movie();
						movie.setTop(top);
						movie.setTitle(title);
						movie.setRealtime(realtime);
						movie.setBoxofficeof(boxofficeof);
						movie.setRowseatof(rowseatof);
						movie.setTotal(total);
						movie.setDatas(datas);
						movie.setId(id);
						
						movies.add(movie);
						
						
					}
					
					
					
					
					Element dcv = doc.getElementsByClass("banner_right").get(0);
					Elements dbv=dcv.children();
					
					for (int i = 0; i < dbv.size(); i++) {
						Element a=dbv.get(i);
						Elements b=a.getElementsByTag("dt");
						Element d=b.get(0).getElementById("rImg");
						String picurl=d.getElementsByTag("img").attr("src");
						
						Elements e=a.getElementsByTag("dd");
						
						String rCountry=e.get(0).getElementById("rCountry").text();
						String rRuntime=e.get(0).getElementById("rRuntime").text();
						String rDaoyan=e.get(0).getElementById("rDaoyan").text();
						String rBianju=e.get(0).getElementById("rBianju").text();
						String rYanyuan=e.get(0).getElementById("rYanyuan").text();
						
						Movie movie=movies.get(i);
						movie.setPicurl(picurl);
						movie.setrCountry(rCountry);
						movie.setrRuntime(rRuntime);
						movie.setrDaoyan(rDaoyan);
						movie.setrBianju(rBianju);
						movie.setrYanyuan(rYanyuan);
						
						imgurl=new ArrayList<String>();
						imgurl.add(picurl);
						
						
//						DownloadImage(picurl, movies.get(i).getId());
						
					}
					
				} catch (Exception e) {
				}
				
				return movies;
			}
				
				
			protected void onPostExecute(List<Movie> movies) {
				TopModelImpl.this.movies=movies;
				Log.v("info", "onPost-----movies.size()++++++"+movies.size());
				callback.onSuccess(movies);
			}
			
		};
		task.execute();
		
		return null;
	}
	
	
	
	
	
	private void DownloadImage(String imguri,String imgname)  
	{  
	    URL url;  
	    byte[] b=null;  
	    try {  
	        url = new URL(imguri);   //����URL  
	        HttpURLConnection con;  
	        con = (HttpURLConnection)url.openConnection();  //������  
	        con.setRequestMethod("GET"); //�������󷽷�  
	        //�������ӳ�ʱʱ��Ϊ5s  
	        con.setConnectTimeout(5000);  
	        InputStream in=con.getInputStream();  //ȡ���ֽ�������  
	        b=readInputStream(in);  
	        Log.v("Save","getbyte");  
			Bitmap bitmap=decodeSampledBitmapFromStream(b,1000, 2000);
	        try  
	        {  
	            saveJPGFile(bitmap, imgname);  
	        }  
	        catch(Exception e)  
	        {  
	        }  
	    } catch (Exception e) {  
	          
	        e.printStackTrace();  
	    }  
	}  
	  
	/** 
	 *  
	 * @param bm 
	 *            ͼƬ��bitmap 
	 * @param fileName 
	 *            �ļ��� 
	 * @param folderName 
	 *            �ļ����� 
	 * @throws IOException 
	 */  
	private void saveJPGFile(Bitmap bm,String fileName)  
	        throws IOException {  
		
//		File file = new File("sdcard/myfloder");
//		if(!file.exists()){
//			file.mkdirs();
//		}
	    String path = "sdcard/myfloder";  
	    File dirFile = new File(path);  
	    // �ļ��в������򴴽��ļ���  
	    if (!dirFile.exists()) {  
	        dirFile.mkdirs();  
	    }  
	    File myCaptureFile = new File(path + fileName + ".jpg");  
	      
	    try{  
	    BufferedOutputStream bos = new BufferedOutputStream(  
	            new FileOutputStream(myCaptureFile));  
	    bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);  
	    bos.flush();  
	    bos.close();  
	    if(bm.isRecycled()==false)  
	    {  
	        bm.recycle();  
	    }  
	    }catch(Exception e){  
	          
	    }  
	}  
	  
	private byte[] readInputStream(InputStream in) throws Exception{  
	    int len=0;  
	    byte buf[]=new byte[1024];  
	    ByteArrayOutputStream out=new ByteArrayOutputStream();  
	    while((len=in.read(buf))!=-1){  
	        out.write(buf,0,len);  //������д���ڴ�  
	    }  
	    out.close();  //�ر��ڴ������  
	    return out.toByteArray(); //���ڴ������ת����byte����  
	}  
	  
	private Bitmap decodeSampledBitmapFromStream(byte[] b,int reqWidth, int reqHeight) {   
	    final BitmapFactory.Options options = new BitmapFactory.Options();    
	    options.inJustDecodeBounds = true;    
	    BitmapFactory.decodeByteArray(b, 0, b.length, options);   
	    // �������涨��ķ�������inSampleSizeֵ    
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);    
	    // ʹ�û�ȡ����inSampleSizeֵ�ٴν���ͼƬ    
	    options.inJustDecodeBounds = false;   
	    return BitmapFactory.decodeByteArray(b, 0, b.length, options);  
	}  
	private int calculateInSampleSize(BitmapFactory.Options options,    
	        int reqWidth, int reqHeight) {    
	    // ԴͼƬ�ĸ߶ȺͿ��    
	    final int height = options.outHeight;    
	    final int width = options.outWidth;    
	    int inSampleSize = 1;    
	    if (height > reqHeight || width > reqWidth) {    
	        // �����ʵ�ʿ�ߺ�Ŀ���ߵı���    
	        final int heightRatio = Math.round((float) height / (float) reqHeight);    
	        final int widthRatio = Math.round((float) width / (float) reqWidth);    
	        // ѡ���͸�����С�ı�����ΪinSampleSize��ֵ���������Ա�֤����ͼƬ�Ŀ�͸�    
	        // һ��������ڵ���Ŀ��Ŀ�͸ߡ�    
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;    
	    }    
	    return inSampleSize;    
	}  
	
	
	public List<Bitmap> loadtitle(final AsyncCallback callback) {
		AsyncTask<String, String, List<Bitmap>> task=new AsyncTask<String, String, List<Bitmap>>() {
			protected List<Bitmap> doInBackground(String... params) {
				imgbms=new ArrayList<Bitmap>();
				for (int i = 0; i < movies.size(); i++) {
					File file=new File("sdcard/myfloder"+movies.get(i).getId()+".jpg");  
					try {  
						if(file.exists())  
						{  
							//�ļ�����,���ػ�ȡ  
							FileInputStream fis;  
							try {  
								fis = new FileInputStream(file);  
								
								Bitmap bitmap=BitmapFactory.decodeStream(fis);
								imgbms.add(bitmap);
								fis.close();  
							} catch (FileNotFoundException e) {  
								e.printStackTrace();  
							} catch (IOException e) {  
								e.printStackTrace();  
							}  
						}  
						else  
						{  
							for (int j = 0; j < movies.size(); j++) {
								DownloadImage(movies.get(j).getPicurl(), movies.get(j).getId());
							}
						}  
						
					} catch (Exception e) {  
						e.printStackTrace();  
					}  
				}
				return imgbms;
			}
			protected void onPostExecute(List<Bitmap> imgbms) {
				callback.onSuccess(imgbms);
			}
		};
		task.execute();
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
