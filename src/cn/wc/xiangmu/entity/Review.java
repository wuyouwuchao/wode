package cn.wc.xiangmu.entity;

public class Review {
	String iamgesrc;
	String title;
	
	String time;
	String txt;
	String link;
	public String getIamgesrc() {
		return iamgesrc;
	}
	public void setIamgesrc(String iamgesrc) {
		this.iamgesrc = iamgesrc;
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
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Review(String iamgesrc, String title, String time,
			String txt, String link) {
		super();
		this.iamgesrc = iamgesrc;
		this.title = title;
		
		this.time = time;
		this.txt = txt;
		this.link = link;
	}
	public Review() {
		super();
	}
	
}
