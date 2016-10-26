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
				  //����NotificationManager
		        String ns = Context.NOTIFICATION_SERVICE;
		        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		        //����֪ͨ��չ�ֵ�������Ϣ
		        int icon = R.drawable.xiala;
		        CharSequence tickerText = "�ҵ�֪ͨ������";
		        long when = System.currentTimeMillis();
		        Notification notification = new Notification(icon, tickerText, when);
		         
		        //��������֪ͨ��ʱҪչ�ֵ�������Ϣ
		        Context context = getApplicationContext();
		        CharSequence contentTitle = "���ղصĵ�ӰҪ��ӳ��";
		        CharSequence contentText = "���ղصĵ�Ӱ"+cart.getItems().get(i).getPreMovieb().getTitle()+"����һ�����ӳ��";
		        Intent notificationIntent = new Intent(this, MainActivity.class);
		        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
		                notificationIntent, 0);
		        notification.setLatestEventInfo(context, contentTitle, contentText,
		                contentIntent);
		         
		        //��mNotificationManager��notify����֪ͨ�û����ɱ�������Ϣ֪ͨ
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
