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
	        url = new URL(imguri);   //设置URL  
	        HttpURLConnection con;  
	        con = (HttpURLConnection)url.openConnection();  //打开连接  
	        con.setRequestMethod("GET"); //设置请求方法  
	        //设置连接超时时间为5s  
	        con.setConnectTimeout(5000);  
	        InputStream in=con.getInputStream();  //取得字节输入流  
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
	 *            图片的bitmap 
	 * @param fileName 
	 *            文件名 
	 * @param folderName 
	 *            文件夹名 
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
	    // 文件夹不存在则创建文件夹  
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
	        out.write(buf,0,len);  //把数据写入内存  
	    }  
	    out.close();  //关闭内存输出流  
	    return out.toByteArray(); //把内存输出流转换成byte数组  
	}  
	  
	private Bitmap decodeSampledBitmapFromStream(byte[] b,int reqWidth, int reqHeight) {   
	    final BitmapFactory.Options options = new BitmapFactory.Options();    
	    options.inJustDecodeBounds = true;    
	    BitmapFactory.decodeByteArray(b, 0, b.length, options);   
	    // 调用上面定义的方法计算inSampleSize值    
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);    
	    // 使用获取到的inSampleSize值再次解析图片    
	    options.inJustDecodeBounds = false;   
	    return BitmapFactory.decodeByteArray(b, 0, b.length, options);  
	}  
	private int calculateInSampleSize(BitmapFactory.Options options,    
	        int reqWidth, int reqHeight) {    
	    // 源图片的高度和宽度    
	    final int height = options.outHeight;    
	    final int width = options.outWidth;    
	    int inSampleSize = 1;    
	    if (height > reqHeight || width > reqWidth) {    
	        // 计算出实际宽高和目标宽高的比率    
	        final int heightRatio = Math.round((float) height / (float) reqHeight);    
	        final int widthRatio = Math.round((float) width / (float) reqWidth);    
	        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高    
	        // 一定都会大于等于目标的宽和高。    
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
							//文件存在,本地获取  
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
