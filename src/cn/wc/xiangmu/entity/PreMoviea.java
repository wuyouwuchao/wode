package cn.wc.xiangmu.entity;


public class PreMoviea {
	String title;
	//图片路径
	String imgsrc;
	//上映时间
	String time;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public PreMoviea(String title, String imgsrc, String time) {
		super();
		this.title = title;
		this.imgsrc = imgsrc;
		this.time = time;
	}
	public PreMoviea() {
		super();
	}
	
	
}
