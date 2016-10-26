package cn.wc.xiangmu.app;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import cn.wc.xiangmu.R;
import cn.wc.xiangmu.activity.MainActivity;
import cn.wc.xiangmu.entity.Cart;
import cn.wc.xiangmu.entity.PreMovieb;

public class MyApplication extends Application{
	private static MyApplication app;
	private Cart cart;
	private List<PreMovieb> moviebas;
	@SuppressWarnings("deprecation")
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		app=this;
		moviebas=new ArrayList<PreMovieb>();
		cart=new Cart();
		cart = cart.readCart();
		for (int i = 0; i < cart.getItems().size(); i++) {
			int t=cart.getItems().get(i).getPreMovieb().getT();
			if(t==1){
				  //定义NotificationManager
		        String ns = Context.NOTIFICATION_SERVICE;
		        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		        //定义通知栏展现的内容信息
		        int icon = R.drawable.xiala;
		        CharSequence tickerText = "我的通知栏标题";
		        long when = System.currentTimeMillis();
		        Notification notification = new Notification(icon, tickerText, when);
		         
		        //定义下拉通知栏时要展现的内容信息
		        Context context = getApplicationContext();
		        CharSequence contentTitle = "您收藏的电影要首映啦";
		        CharSequence contentText = "您收藏的电影"+cart.getItems().get(i).getPreMovieb().getTitle()+"还有一天就首映啦";
		        Intent notificationIntent = new Intent(this, MainActivity.class);
		        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
		                notificationIntent, 0);
		        notification.setLatestEventInfo(context, contentTitle, contentText,
		                contentIntent);
		         
		        //用mNotificationManager的notify方法通知用户生成标题栏消息通知
		        mNotificationManager.notify(1, notification);
			}
		}
	}
	public Cart getCart(){
		return this.cart;
	}
	
	public static MyApplication getApp(){
		return app;
	}
	
	public List<PreMovieb> getMoviebas() {
		return moviebas;
	}
	public void setMoviebas(List<PreMovieb> moviebas) {
		this.moviebas = moviebas;
	}
	
	
	
}
