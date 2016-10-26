package cn.wc.xiangmu.entity;

public class Yugaopian {
	String iamgeurl;
	String url;
	String title;
	String time;
	public String getIamgeurl() {
		return iamgeurl;
	}
	public void setIamgeurl(String iamgeurl) {
		this.iamgeurl = iamgeurl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Yugaopian(String iamgeurl, String url, String title, String time) {
		super();
		this.iamgeurl = iamgeurl;
		this.url = url;
		this.title = title;
		this.time = time;
	}
	public Yugaopian() {
		super();
	}
	
	
	
}
